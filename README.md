# SityTv
Application that List TV series using TVMaze API.

### Config Project

#### 1. create a file with the name: secrets.properties in the root with the url base for this particular case is: https://api.tvmaze.com/"
the content of secrets.properties must be:
TV_MAZE_BASE_URL=" https://api.tvmaze.com/"

Context: For security you must not expose urls, api keys etc. 


### App Install

The APK is in the root of the project in the branch Master with the name: sitytv.apk downolad it.

when install the apk to a physical device make sure that previously configure your device to accept unknow providers or insecure application.
Or when the dialog shows of app insecure tap install anyway.

### Optional features
1. Unit test added 

### Stack Used
1. MVVM Design pattern
2. Dependency injection with Dagger Hilt
3. Jetpack compose interoperability with xml
4. data binding
5. Room (implemented)
6. Kotlin Flows for reactivity
7. Retrofit
7. Kotlin Coroutines.
