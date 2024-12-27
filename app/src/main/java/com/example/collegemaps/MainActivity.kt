package com.example.collegemaps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.collegemaps.OSRMHelper.processOsrmResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private var userCurrentLocation: GeoPoint? = null
    private var LOCATION_PERMISSION_REQUEST_CODE = 1001
    private var userLocationMarker: Marker? = null
    private val client = OkHttpClient()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance()
            .load(applicationContext, getSharedPreferences("osm_prefs", MODE_PRIVATE))
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.map)
        mapView.setMultiTouchControls(true)

        val customPaths = CustomPaths(mapView)
        val buildingSearch = findViewById<AutoCompleteTextView>(R.id.searchView)

        val buildingNames = BuildingData.buildings.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, buildingNames)

        buildingSearch.setAdapter(adapter)

        buildingSearch.setOnItemClickListener { parent, _, position, _ ->
            val selectedBuilding = parent.getItemAtPosition(position) as String

            BuildingData.handleBuildingClick(selectedBuilding, mapView)

            //hide keyboard after selecting buildling
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(buildingSearch.windowToken, 0)

        }




        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted, request location updates
            requestLocationUpdates()
        }

        // Set map center to a campus building or near it
        val startPoint = GeoPoint(35.350119, -119.104221)
        mapView.controller.setZoom(18.0)
        mapView.controller.setCenter(startPoint)

        BuildingData.buildings.forEach { (_, _) ->
            val buildingPolygon = BuildingPolygon(
                mapView,
            ) { closestEntrance ->
                val currentLocation = getUserCurrentLocation()
                if (currentLocation != null) {
                    getRouteFromGraphhopper(currentLocation, closestEntrance)
                } else {
                    Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show()
                }
            }

            val pathPoints = listOf(
                GeoPoint(35.349876, -119.104205),
                GeoPoint(35.349876, -119.104226),
                GeoPoint(35.349876, -119.104336),
            )
            buildingPolygon.addAllBuildingPolygons()
            customPaths.addPath(pathPoints)
            mapView.invalidate()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, request location updates
                requestLocationUpdates()
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000L)
            .setMinUpdateIntervalMillis(1000L)
            .setMaxUpdateDelayMillis(1000L)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    val currentLat = location.latitude
                    val currentLon = location.longitude
                    userCurrentLocation = GeoPoint(currentLat, currentLon)

                    // Log location updates
                    Log.d("Location", "Latitude: $currentLat, Longitude: $currentLon")

                    // Update or create a marker for the user's location
                    if (userLocationMarker == null) {
                        userLocationMarker = Marker(mapView).apply {
                            position = userCurrentLocation!!
                            title = "Your Location"
                            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        }
                        mapView.overlays.add(userLocationMarker)
                    } else {
                        userLocationMarker!!.position = userCurrentLocation!!
                    }

                    // Optionally move map to user's location
                    mapView.invalidate()
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    fun getUserCurrentLocation(): GeoPoint? {
        return userCurrentLocation
    }

    private fun getRouteFromGraphhopper(currentLocation: GeoPoint, destination: GeoPoint) {
        val apiKey = "7ccbdeb1-5fd9-4466-ae1d-75c588c0d189" // Replace with your actual GraphHopper API key
        val url = "https://graphhopper.com/api/1/route?point=${currentLocation.latitude},${currentLocation.longitude}&point=${destination.latitude},${destination.longitude}&type=json&vehicle=foot&key=$apiKey"

        val request = Request.Builder()
            .url(url)
            .build()

        Thread {
            try {
                // Send the request and get the response
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    // Handle the response if the request is successful
                    val responseBody = response.body?.string()
                    // Replace processGraphhopperResponse with the updated processOsrmResponse
                    processOsrmResponse(responseBody, mapView)
                    response.body?.close()
                } else {
                    // Handle unsuccessful response
                    runOnUiThread {
                        Toast.makeText(this, "Error getting directions", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: IOException) {
                // Handle network failure
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()

    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
