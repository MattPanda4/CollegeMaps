package com.example.collegemaps

import com.google.gson.JsonObject

data class OsrmResponse(
    val routes: List<Route> )
{ data class Route(
        val geometry: JsonObject
    )
}

