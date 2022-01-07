# RELEASE NOTES
__Citys_to_Visit_List__
Android application intended to give out traveling ideas.

## Release Features
### Feature 1
In the *ExploreActivity* there is button "Random" that starts search for random city in the world and lists it's basic information: name, country, region, population, timnezone.

### Feature 2
In the *ExploreActivity* there is switch button through which you can get that citys location on Google Maps.

### Feature 3
Cities that get liked are stored in the *FavoritesActivity*. From there you can access citys Wikipedia page.


## Enchancements
### Echancement 1
When exploring, searching for cities, when searching for next city the previous one gets lost if it isn't added to favorites by liking it. It can happen that user unintentionally relocates to another activity or starts serch for the next city and then loses last search. Undo button is good solution for this and also including redo button in order to navigate through search history.

### Enhancment 2
Cities that are saved in favorites can not be shown in Google Maps again. Clicking on the city in favorites will redirect you to activity where you will be able to toggle between city information and location on Google Maps.

## Known Issues and Problems
### Issue 1
When you exit from *ExploreActivity* back to *MainActivity* or go to *FavoritesActivity* city information dissappears.
#### Workaround
Like button gives option to add randomly found city to favorites so it can be found in *FavoritesActivity* and from there user can access Wikipedia page of that city by clicking on the city name and then url to Wiki page.

### Issue 2
API works with only 1 request/second. That means when searching for city you can get search results for at most one city every second. \


## 

This was project for Basics of mobile and web development course.

https://rapidapi.com/ \
https://wft-geo-db.p.rapidapi.com/v1/geo/cities


JSON DATA FETCHING AND PARSING FROM URL \
https://www.youtube.com/watch?v=Vcn4OuV4Ixg&list=LL&index=1&t=798s \


How do you set an integer value with JSONObject in Java?
https://stackoverflow.com/questions/37999822/how-do-you-set-an-integer-value-with-jsonobject-in-java/37999997


How to Switch Between Activities \
https://www.youtube.com/watch?v=6RtF_mbHcEc&t=3s


How to send an object from one Android Activity to another using Intents?
https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents


How to Open a New Activity and Pass Variables to It \
https://www.youtube.com/watch?v=eL69kj-_Wvs


Memory address of variables in Java \
obj.hashCode() --> Log.i("My Tag", obj.hashCode());

Instance of object \
obj instanceof object --> Log.i("My Tag", String.valueOf(obj instanceof object));



Fragments in Android Studio + Switch button in Android \
https://www.youtube.com/watch?v=2v5AxQK-A2I&t=170s



How can I parse a local JSON file from assets folder into a ListView?
https://stackoverflow.com/questions/19945411/how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listview/19945484#19945484



Create, Write, Edit JSON file in Android Studio \
https://stackoverflow.com/questions/62474129/create-write-edit-json-file-in-android-studio \



Convert a String to a byte array and then back to the original String \
https://stackoverflow.com/questions/7947871/convert-a-string-to-a-byte-array-and-then-back-to-the-original-string


Write Text File to Internal Storage \
https://www.youtube.com/watch?v=EcfUkjlL9RI


