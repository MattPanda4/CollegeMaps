package com.example.collegemaps

import android.graphics.Paint
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polygon




class BuildingPolygon(
    private val mapView: MapView,
    private val onBuildingClick: (closestEntrance: GeoPoint) -> Unit,
) {

    private val buildingEntrances = mapOf(

        //"Fix the entrance locations, started using random ones to make sure they populate"),

        "Dorothy Donohue Hall" to listOf(
            GeoPoint(35.350417, -119.104139), // Entrance 1
            GeoPoint(35.350667, -119.103639), // Entrance 2
            GeoPoint(35.350194, -119.103639), // Entrance 3
            GeoPoint(35.350417, -119.102889)  // Entrance 4
        ),
        "Express Cafe" to listOf(
            GeoPoint(35.349856, -119.10433)
        ),
        "Performance Hall" to listOf(
            GeoPoint(35.351033, -119.104350)
        ),
        "Lecture Building" to listOf(
            GeoPoint(35.350978, -119.105213)
        ),
        "Administration East" to listOf(
            GeoPoint(35.350670, -119.105178)
        ),
        "Fine Arts" to listOf(
            GeoPoint(35.351376, -119.104972)
        ),
        "Classroom Building" to listOf(
            GeoPoint(35.351326, -119.105455)
        ),
        "Dore Theatre" to listOf(
            GeoPoint(35.352044, -119.105431)
        ),
        "Music Building" to listOf(
            GeoPoint(35.351753, -119.105841)
        ),
        "Humanities Complex" to listOf(
            GeoPoint(35.351844, -119.106742)
        ),
        "Visual Arts" to listOf(
            GeoPoint(35.351844, -119.106742)
        ),
        "Education Building" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Student Services" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Romberg Nursing Center" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Central Plant Operations" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Administration" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Administration West" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "University Advancement" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Science Building I" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Science Building II" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Science Building III" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Runner Cafe" to listOf(
            GeoPoint(35.349856, -119.10433)
        ),
        "Modular East" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Greenhouse" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "SRC" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "BookStore" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Walter Stiern Library" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Campus Police" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Physical Education Building" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Icardo Center" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Student Health Services" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Fab lab" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Engineering Complex II" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Engineering Complex III" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Extended Education" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Business Development Center I" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Business Development Center II" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Business Development Center III" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Kegley Center" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Lorien Hall" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "International Students Office" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),

        "J Antonio Sports Center" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Barnes Beach Volleyball Complex" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "John S. Hillman Aquatic Center" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Soccer Field" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "J.R Hillman Aquactic Center" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Dorby Hall" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Rivendel Hall" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Numenor" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Rohan" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Facilities and Corporation Yard" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "WellCore Repository" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Modular West" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Child Care" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Satellite Plant" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "SoftBall Field" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Track Field" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Hardt Field" to listOf(
            GeoPoint(35.350527, -119.104529)
        ),
        "Track Field" to listOf(
            GeoPoint(35.350527, -119.104529)
        )
    )

            // Function to get the closest entrance to the user
    private fun getClosestEntrance(userLocation: GeoPoint, entrances: List<GeoPoint>): GeoPoint {
        return entrances.minByOrNull { entrance -> userLocation.distanceToAsDouble(entrance) }
            ?: entrances.first()
    }

    // Function to create and add a building polygon with a click listener
    private fun addBuildingPolygon(buildingName: String) {
        val buildingPoints = BuildingData.buildings[buildingName] ?: return
        val entrances = buildingEntrances[buildingName] ?: return

        val buildingPolygon = Polygon().apply {
            points = buildingPoints + buildingPoints.first() // Close the polygon
            fillPaint.style = Paint.Style.FILL
            fillPaint.color = 0x0000FF00 // Transparent green
            outlinePaint.style = Paint.Style.STROKE
            outlinePaint.color = 0x3300FF00 // Solid green
            outlinePaint.strokeWidth = 5.0f
        }

        // Set the click listener for the building polygon
        buildingPolygon.setOnClickListener { _, _, _ ->
            val currentLocation = (mapView.context as? MainActivity)?.getUserCurrentLocation()
                ?: return@setOnClickListener true
            val closestEntrance = getClosestEntrance(currentLocation, entrances)
            onBuildingClick(closestEntrance)

            // Remove the old polyline if it exists


            // Construct OSRM URL for routing
            val osrmUrl = "http://router.project-osrm.org/route/v1/driving/${currentLocation.longitude},${currentLocation.latitude};${closestEntrance.longitude},${closestEntrance.latitude}?overview=full&steps=true"

            // Make a request to OSRM (or call the function directly from MainActivity)
            val client = OkHttpClient()
            val request = Request.Builder().url(osrmUrl).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        OSRMHelper.processOsrmResponse(response.body?.string(), mapView ) // Use the processOsrmResponse method from OSRMHelper
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(mapView.context, "Failed to get route", Toast.LENGTH_SHORT).show()
                }
            })

            true
        }

        // Add the building polygon to the map
        mapView.overlays.add(buildingPolygon)
    }

    // Function to add all building polygons
    fun addAllBuildingPolygons() {
        BuildingData.buildings.keys.forEach { buildingName ->
            addBuildingPolygon(buildingName)
        }
    }

}



