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




    fun processOsrmResponse(responseBody: String?, mapView: MapView) {
        if (responseBody.isNullOrEmpty()) {
            showToast(mapView.context, "No response from Graphhopper")
            return
        }

        try {
            val jsonResponse = JSONObject(responseBody)
            val paths = jsonResponse.getJSONArray("paths")
            if (paths.length() > 0) {
                val path = paths.getJSONObject(0) // Use the first path
                val geometry = path.getString("points") // This will be in polyline format

                // Decode the polyline into GeoPoints (same method as before)
                val polylinePoints = decodePolyline(geometry)

                if (polylinePoints.isEmpty()) {
                    showToast(mapView.context, "Failed to decode polyline")
                    return
                }

                // Remove any old polylines before adding the new one
                mapView.overlays.filterIsInstance<Polyline>().forEach {
                    mapView.overlays.remove(it)
                }

                // Create and add new polyline (this is the correct route from Graphhopper)
                val polyline = Polyline()
                polylinePoints.forEach { geoPoint -> polyline.addPoint(geoPoint) }

                // Set the route appearance (color and thickness)
                polyline.outlinePaint.color = 0xFF0000FF.toInt() // Blue color for Graphhopper route
                polyline.outlinePaint.strokeWidth = 5.0f
                mapView.overlays.add(polyline)
            } else {
                showToast(mapView.context, "No route found")
            }
        } catch (e: Exception) {
            Log.e("Graphhopper Response", "Error processing Graphhopper response: ${e.message}")
        }
    }




    private fun decodePolyline(encoded: String): List<GeoPoint> {
        val polyline = mutableListOf<GeoPoint>()
        var index = 0
        var lat = 0
        var lng = 0


        if (encoded.isEmpty()) {
            Log.e("Decode Polyline", "Encoded polyline is empty or invalid")
            return emptyList()
        }
        if (encoded.any { it.code - 63 < 0 }) {
            Log.e("Decode Polyline", "Invalid character in encoded polyline")
            return emptyList()
        }

        try {
            while (index < encoded.length) {
                var b: Int
                var shift = 0
                var result = 0
                do {
                    if (index >= encoded.length) {
                        Log.e("Decode Polyline", "Index out of bounds at index: $index during latitude decoding")
                        return emptyList()
                    }
                    b = encoded[index++].code - 63
                    result = result or ((b and 0x1f) shl shift)
                    shift += 5
                } while (b >= 0x20)

                val dLat = if (result and 1 != 0) -(result shr 1) else result shr 1
                lat += dLat

                shift = 0
                result = 0
                do {
                    if (index >= encoded.length) {
                        Log.e("Decode Polyline", "Index out of bounds at index: $index during longitude decoding")
                        return emptyList()
                    }
                    b = encoded[index++].code - 63
                    result = result or ((b and 0x1f) shl shift)
                    shift += 5
                } while (b >= 0x20)

                val dLng = if (result and 1 != 0) -(result shr 1) else result shr 1
                lng += dLng

                polyline.add(GeoPoint(lat / 1E5, lng / 1E5))
            }
        } catch (e: Exception) {
            Log.e("Decode Polyline", "Error decoding polyline: ${e.message}")
            e.printStackTrace()
        }

        if (polyline.isEmpty()) {
            Log.e("Decode Polyline", "Decoded polyline is empty")
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

