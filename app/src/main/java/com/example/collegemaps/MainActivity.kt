package com.example.collegemaps

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        val startPoint = GeoPoint(35.3430, -119.1099)
        mapView.controller.setZoom(18.0)
        mapView.controller.setCenter(startPoint)

        // Add building polygons for each building
        BuildingData.buildings.forEach { (_, _) ->
            val buildingPolygon = BuildingPolygon(
                mapView,
            ) { closestEntrance ->
                val currentLocation = getUserCurrentLocation()
                if (currentLocation != null) {
                    getDirectionsFromOSRM(currentLocation, closestEntrance)
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
    // Handle permission request result
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
    // Function to request location updates
    private fun requestLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000L)
            .setMinUpdateIntervalMillis(50L)
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
                    mapView.controller.animateTo(userCurrentLocation)
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

    // Function to get the current user location
    fun getUserCurrentLocation(): GeoPoint? {
        return userCurrentLocation
    }
    // Function to get directions from OSRM
    private fun getDirectionsFromOSRM(currentLocation: GeoPoint, destination: GeoPoint) {
        val url = "http://router.project-osrm.org/route/v1/walking/" +
                "${currentLocation.longitude},${currentLocation.latitude};" +
                "${destination.longitude},${destination.latitude}"

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
                    onOsrmResponseReceived(responseBody)
                    response.body?.close()
                    // Process the response
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
    private fun onOsrmResponseReceived(response: String?) {
        OSRMHelper.processOsrmResponse(response, mapView)
    }
    // Lifecycle methods for mapView
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}





