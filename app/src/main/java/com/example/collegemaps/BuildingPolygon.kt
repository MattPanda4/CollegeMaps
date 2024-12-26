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
import org.osmdroid.views.overlay.Polyline


class BuildingPolygon(
    private val mapView: MapView,
    private val onBuildingClick: (closestEntrance: GeoPoint) -> Unit,
) {

    private val buildingEntrances = mapOf(

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
            GeoPoint(35.351107, -119.104323),
            GeoPoint(35.350970, -119.105224)
        ),
        "Lecture Building" to listOf(
            GeoPoint(35.350970, -119.105224)
        ),
        "Administration East" to listOf(
            GeoPoint(35.350504, -119.104864)
        ),
        "Fine Arts" to listOf(
            GeoPoint(35.351271, -119.104950)
        ),
        "Classroom Building" to listOf(
            GeoPoint(35.351175, -119.105205)
        ),
        "Dore Theatre" to listOf(
            GeoPoint(35.352054, -119.105202)
        ),
        "Music Building" to listOf(
            GeoPoint(35.351893, -119.105870)
        ),
        "Humanities Complex" to listOf(
            GeoPoint(35.351917, -119.106885)
        ),
        "Visual Arts" to listOf(
            GeoPoint(35.351615, -119.107089)
        ),
        "Education Building" to listOf(
            GeoPoint(35.350386, -119.104507)
        ),
        "Student Services" to listOf(
            GeoPoint(35.350377, -119.104916)
        ),
        "Romberg Nursing Center" to listOf(
            GeoPoint(35.349659, -119.104659)
        ),
        "Central Plant Operations" to listOf(
            GeoPoint(35.349657, -119.105085)
        ),
        "Administration" to listOf(
            GeoPoint(35.350468, -119.105377)
        ),
        "Administration West" to listOf(
            GeoPoint(35.350436, -119.106029)
        ),
        "University Advancement" to listOf(
            GeoPoint(35.350420, -119.106316)
        ),
        "Science Building I" to listOf(
            GeoPoint(35.349712, -119.103862)
        ),
        "Science Building II" to listOf(
            GeoPoint(35.349646, -119.103176)
        ),
        "Science Building III" to listOf(
            GeoPoint(35.349077, -119.103704)
        ),
        "Runner Cafe" to listOf(
            GeoPoint(35.350685, -119.102352)
        ),
        "Modular East" to listOf(
            GeoPoint(35.350604, -119.101285)
        ),
        "Greenhouse" to listOf(
            GeoPoint(35.350425, -119.101242)
        ),
        "SRC" to listOf(
            GeoPoint(35.349078, -119.102051)
        ),
        "BookStore" to listOf(
            GeoPoint(35.349983, -119.101665)
        ),
        "Walter Stiern Library" to listOf(
            GeoPoint(35.351424, -119.103414)
        ),
        "Campus Police" to listOf(
            GeoPoint(35.348797, -119.102964)
        ),
        "Physical Education Building" to listOf(
            GeoPoint(35.348279, -119.102821)
        ),
        "Icardo Center" to listOf(
            GeoPoint(35.347577, -119.102827)
        ),
        "Student Health Services" to listOf(
            GeoPoint(35.347824, -119.103851)
        ),
        "Fab lab" to listOf(
            GeoPoint(35.348027, -119.104715)
        ),
        "Engineering Complex II" to listOf(
            GeoPoint(35.347841, -119.104710)
        ),
        "Engineering Complex III" to listOf(
            GeoPoint(35.347913, -119.105056)
        ),
        "Extended Education" to listOf(
            GeoPoint(35.348386, -119.104900)
        ),
        "Business Development Center I" to listOf(
            GeoPoint(35.349086, -119.105056)
        ),
        "President's Office" to listOf(
            GeoPoint(35.348782, -119.104747)
        ),
        "Business Development Center III" to listOf(
            GeoPoint(35.348823, -119.105294)
        ),
        "Kegley Center" to listOf(
            GeoPoint(35.350193, -119.106828)
        ),
        "Lorien Hall" to listOf(
            GeoPoint(35.349572, -119.106930)
        ),
        "International Students Office" to listOf(
            GeoPoint(35.349817, -119.107175)
        ),

        "J Antonio Sports Center" to listOf(
            GeoPoint(35.348290, -119.101741)
        ),
        "Barnes Beach Volleyball Complex" to listOf(
            GeoPoint(35.348073, -119.101488)
        ),
        "John S. Hillman Aquatic Center" to listOf(
            GeoPoint(35.347881, -119.101494)
        ),
        "Soccer Field" to listOf(
            GeoPoint(35.347601, -119.101571)
        ),
        "J.R Hillman Aquactic Center" to listOf(
            GeoPoint(35.347808, -119.101818)
        ),
        "Dorby Hall" to listOf(
            GeoPoint(35.349620, -119.107608)
        ),
        "Rivendel Hall" to listOf(
            GeoPoint(35.349755, -119.108081)
        ),
        "Numenor" to listOf(
            GeoPoint(35.350079, -119.108154)
        ),
        "Rohan" to listOf(
            GeoPoint(35.350399, -119.107888)
        ),
        "Facilities and Corporation Yard" to listOf(
            GeoPoint(35.345402, -119.103892)
        ),
        "WellCore Repository" to listOf(
            GeoPoint(35.344621, -119.103846)
        ),
        "Modular West" to listOf(
            GeoPoint(35.349095, -119.107158)
        ),
        "Child Care" to listOf(
            GeoPoint(35.349084, -119.107679)
        ),
        "Satellite Plant" to listOf(
            GeoPoint(35.349149, -119.108266)
        ),
        "SoftBall Field" to listOf(
            GeoPoint(35.346277, -119.101106)
        ),
        "Track Field" to listOf(
            GeoPoint(35.344920, -119.102108)
        ),
        "Hardt Field" to listOf(
            GeoPoint(35.347607, -119.107498)
        )
    )

    private fun getClosestEntrance(userLocation: GeoPoint, entrances: List<GeoPoint>): GeoPoint {
        return entrances.minByOrNull { entrance -> userLocation.distanceToAsDouble(entrance) }
            ?: entrances.first()
    }

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
            mapView.overlays.removeAll { it is Polyline }

            // Construct OSRM URL for routing
            val osrmUrl = "http://router.project-osrm.org/route/v1/walking/${currentLocation.longitude},${currentLocation.latitude};${closestEntrance.longitude},${closestEntrance.latitude}?overview=full&steps=true"

            val client = OkHttpClient()
            val request = Request.Builder().url(osrmUrl).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        OSRMHelper.processOsrmResponse(response.body?.string(), mapView) // Process OSRM response
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(mapView.context, "Failed to get route", Toast.LENGTH_SHORT).show()
                }
            })
            true
        }

        mapView.overlays.add(buildingPolygon)
    }

    fun addAllBuildingPolygons() {
        BuildingData.buildings.keys.forEach { buildingName ->
            addBuildingPolygon(buildingName)
        }
    }
}



