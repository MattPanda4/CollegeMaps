package com.example.collegemaps

import android.content.Context
import android.os.Handler
import android.os.Looper

import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import org.json.JSONObject
import android.util.Log
import android.widget.Toast

object OSRMHelper {

    // Function to process the OSRM response and draw the polyline
    fun processOsrmResponse(responseBody: String?, mapView: MapView) {
        if (responseBody.isNullOrEmpty()) {
            showToast(mapView.context, "No response from OSRM")
            return
        }

        try {
            val jsonResponse = JSONObject(responseBody)
            val routes = jsonResponse.getJSONArray("routes")

            if (routes.length() > 0) {
                val route = routes.getJSONObject(0)

                // Get the geometry (Polyline string or GeoJSON object)
                val geometry = route.optString("geometry", "")

                if (geometry.isNotEmpty()) {
                    // Decode the polyline (if it's a polyline string)
                    val polylinePoints = decodePolyline(geometry)

                    // Remove old polyline before adding new one
                    mapView.overlays.filterIsInstance<Polyline>().forEach {
                        mapView.overlays.remove(it)
                    }

                    // Create and add new polyline
                    val polyline = Polyline()
                    polylinePoints.forEach { polyline.addPoint(it) }

                    // Set route appearance (color and thickness)
                    polyline.outlinePaint.color = 0xFF000000.toInt() // Set route color to black
                    polyline.outlinePaint.strokeWidth = 5.0f
                    mapView.overlays.add(polyline)
                } else {
                    // Handle GeoJSON case (geometry is an object)
                    val geometryObject = route.getJSONObject("geometry")
                    val coordinates = geometryObject.getJSONArray("coordinates")
                    val polyline = Polyline()

                    // Iterate over the coordinates and check if each index is valid
                    for (i in 0 until coordinates.length()) {
                        if (coordinates.length() > i) {
                            try {
                                val point = coordinates.getJSONArray(i)
                                val latitude = point.getDouble(1) // Latitude is the second element
                                val longitude = point.getDouble(0) // Longitude is the first element
                                polyline.addPoint(GeoPoint(latitude, longitude))
                            } catch (e: Exception) {
                                Log.w("Warning", "Error processing coordinates at index $i: ${e.message}")
                            }
                        } else {
                            Log.w("Warning", "Index $i is out of bounds for coordinates array")
                        }
                    }

                    // Remove old polyline before adding new one
                    mapView.overlays.filterIsInstance<Polyline>().forEach {
                        mapView.overlays.remove(it)
                    }

                    // Set route appearance (color and thickness)
                    polyline.outlinePaint.color = 0xFF000000.toInt() // Set route color to black
                    polyline.outlinePaint.strokeWidth = 5.0f
                    mapView.overlays.add(polyline)
                }
            } else {
                showToast(mapView.context, "No route found")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    // Function to decode the polyline geometry from OSRM response
    private fun decodePolyline(encoded: String): List<GeoPoint> {
        val polyline = mutableListOf<GeoPoint>()
        var index = 0
        var lat = 0
        var lng = 0

        while (index < encoded.length) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or ((b and 0x1f) shl shift)
                shift += 5
            } while (b >= 0x20)

            val dLat = if (result and 1 != 0) -(result shr 1) else result shr 1
            lat += dLat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or ((b and 0x1f) shl shift)
                shift += 5
            } while (b >= 0x20)

            val dLng = if (result and 1 != 0) -(result shr 1) else result shr 1
            lng += dLng

            polyline.add(GeoPoint(lat / 1E5, lng / 1E5))
        }
        return polyline
    }

    private fun showToast(context: Context, message: String) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } else {
            // Run on UI thread
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

