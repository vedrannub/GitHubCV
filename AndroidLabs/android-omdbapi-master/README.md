# Android-omdbapi

This is an Android Application project which demonstrates implementation of Open Movie Database([OMBD]) in Android using Native JSON Classes.

Native JSON Classes like

 - [JSONObject]
 - [JSONArray]
 - [JSONException]

##Project Classes
 1. MainActivity - The Activity Class of the Project which get JSON from Remote URL and represents the JSON Data into Views of Activity.
 2. MovieDetails - It's Simple Class which maps JSON Data to Class Members.

##Resources
Resources Includes Layout file of the MainActivity(activity_main.xml) and other resources files such as strings.xml, stylex.xml, App Icon files etc.

##Project Functionality
User can enter the movie name in a textbox given, hit search movie button and based on movie name, fetching results from OMDB Api and showing results in Controls in the Activity.


[OMBD]: <http://omdbapi.com/>
[JSONObject]: <http://developer.android.com/reference/org/json/JSONObject.html>
[JSONArray]: <http://developer.android.com/reference/org/json/JSONArray.html>
[JSONException]: <http://developer.android.com/reference/org/json/JSONException.html>