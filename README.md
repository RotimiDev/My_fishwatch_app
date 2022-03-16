# FishWatch App :fish:

This is an Android demo application that displays the various information gotten from this sea animals endpoint https://www.fishwatch.gov/api/species            

The simple app contains two screens. The first screen display a list of sea animals information and has three details per item on the list, which are  **species name, Habitat Impacts** and **the display image of the Species Illustration Photo** src. When users click on an item on the list, it navigate to the second screen which displays list of the content of the Image Gallery. 

**:blowfish:Libraries/Frameworks used**
* MVVM architecture pattern
* Navigation component to help implement effective navigation
* Retrofit for REST api calls
* Glide & Coil for image loading
* Mockito framework & JUnit 5 via android-junit5 for Unit testing
* DaggerHilt for Dependency injection
* Coroutine flow for Threading
* LiveData for lifecycle-awareness
* Connectivity Manager to monitor network connection
* Error handling - ‘ACCESS_NETWORK_STATE’ permission to help App not to crash when network is turned off 
* ViewBinding & DataBinding to bind UI components in layouts to data sources in the app
* 100% KOTLIN


**:blowfish:How to install, run and test the app**
* Using android studio emulation - Copy this link **git@github.com:RotimiDev/My_fishwatch_app.git** and clone this repo
* Allow it to build (**Please be sure your android studio default Gradle JDK is on version 11 which was used in this project**)
* Now run the app on your emulator

For better experience you can **install the APK version on your device** using this link - https://appsenjoy.com/xBJhp
or run the app directly from android studio using a USB and your android phone.

**:blowfish:Future Improvement**
* Fix searchView bug
* Run full unit and UI testing
* Fix second screen issue
* Add a nav drawer with menu
