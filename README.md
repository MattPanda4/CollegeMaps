# College Maps Project
This app allows users to interact with a campus map using OpenStreetMap (OSM) data. It provides the following key features:

OSRM Routing: The app uses OSRM (Open Source Routing Machine) to calculate optimal routes between locations, displayed as polyline paths with customizable colors and stroke widths. The polyline data is decoded from OSRM's route response for accurate visualization.

Building Points: Specific building locations are stored as GeoPoint objects, and users can tap on these points to receive directions to the building’s entrance.

Polygon Highlights: The app highlights campus areas using transparent filled polygons drawn with GeoPoint coordinates. This allows users to visually identify specific campus zones.

Interactive Map: OpenStreetMap is used for mapping, providing a scalable and customizable base map layer for the campus.

Modular Code: The code is modular, separating different functionalities for easier maintenance and scalability, and adhering to clean coding practices by trying to keep most logic out of MainActivity.

The app allows for an easy-to-use interface to explore campus locations and navigate with precision using both OSRM routing and OSM map layers.

## TODO AND FEATURES TO ADD:
- [ ]  Upload a video walkthrough
- [ ] Finish outlining all buildings
- [ ] Add search bar so students can search by the typing rather than just looking for the building on the map
- [ ] Make it so that the routes are more accurate
- [ ] Restrict map area to the campus
- [ ] Add a feature where students can add upcoming activites that are planned for that specific buildling(if theres a science club meeting in Science III then it will show up under a seperate "Upcoming activities" tab as well)
- [ ] Update the user marker to something better
- [ ] Add custom routes because the current routes provided are not as accurate(related to the 3rd problem)

For now, I just wanted to get this out in the open, and make changes as I continue to work on this, something that I have been wanting to do for a few years now and finally getting around to it, excited to see what the finished project will look like.
