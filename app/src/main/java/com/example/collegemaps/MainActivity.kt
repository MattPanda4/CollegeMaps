package com.example.collegemaps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance()
            .load(applicationContext, getSharedPreferences("osm_prefs", MODE_PRIVATE))
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.map)
        mapView.setMultiTouchControls(true)

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
        val startPoint = GeoPoint(35.3430, -119.1099)  // Example campus center
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
            buildingPolygon.addAllBuildingPolygons()
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
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Update current location when it changes
                val currentLat = location.latitude
                val currentLon = location.longitude
                userCurrentLocation = GeoPoint(currentLat, currentLon)
                Log.d("Location", "Latitude: $currentLat, Longitude: $currentLon")

                // Update or create a marker for the user's location
                if (userLocationMarker == null) {
                    // First time getting location, create the marker
                    userLocationMarker = Marker(mapView)
                    userLocationMarker!!.position = userCurrentLocation!!
                    userLocationMarker!!.title = "Your Location"
                    mapView.overlays.add(userLocationMarker)
                } else {
                    // Update the existing marker position
                    userLocationMarker!!.position = userCurrentLocation!!
                }
            }

            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000L,
            1f,
            locationListener
        )
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000L,
            1f,
            locationListener
        )
    }

    // Function to get the current user location
    fun getUserCurrentLocation(): GeoPoint? {
        return userCurrentLocation
    }

    // Function to get directions from OSRM
    private fun getDirectionsFromOSRM(currentLocation: GeoPoint, destination: GeoPoint) {
        val url = "http://router.project-osrm.org/route/v1/walking/" +
                "${currentLocation.longitude},${currentLocation.latitude};" +
                "${destination.longitude},${destination.latitude}" +
                "?overview=full&geometries=geojson"

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





