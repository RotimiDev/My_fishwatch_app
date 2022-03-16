# FishWatch App :fish:

This is an Android demo application that displays the various information gotten from this sea animals endpoint https://www.fishwatch.gov/api/species            

This simple app is composed of two single screens. The first screen display a list of sea animals information.
The screen has strictly three information per item on the list, which are  **species name, Habitat Impacts** and **display the image of the Species Illustration Photo** src. When users click on an item on the list, it navigate to the second screen which display a list of the content of the Image Gallery. 

**Libraries**
* Retrofit for REST api communication
* Glide & Coil for image loading
* Mockito for Unit test
* DaggerHilt for Dependency injection
* Coroutine flow for Threading
* LiveData
* Connectivity Manager to monitor network connection
* ‘ACCESS_NETWORK_STATE’ permission to help App not to crash when network is turned off 




# How to run app & test
