# Weather App

## Description

The Weather App is an Android application that provides information about the weather in different cities around the world. The app displays a list of cities with their current temperature and allows users to view more detailed weather information and the location of the city on a map. Additionally, the app includes a notification service that shows the current temperature of the day at a specific time, using GPS to get the location.

## Features

- **City List Screen**: Displays a list of cities with their current temperature.
  - Data is fetched using the [OpenWeatherMap API](http://api.openweathermap.org/data/2.5/find?lat=23.68&lon=90.35&cnt=50&appid=e384f9ac095b2109c751d95296f8ea76).
- **City Weather Details Screen**: Displays more detailed weather information and the city's location on a map when a city is selected.
  - Location and weather data are displayed using the [OpenWeatherMap API](https://openweathermap.org/current) and Google Map API.
- **Notification Service**: Shows the current temperature of the day at a specific time using GPS.


## 🛠 Built With

- [Kotlin](https://kotlinlang.org/docs/home.html) - The programming language used for modern Android development.
- [Retrofit](https://square.github.io/retrofit/) - Retrofit is a popular type-safe HTTP client library for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) - Architectural pattern for separating concerns.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection frameworks.
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - For managing asynchronous tasks.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn"t destroyed on UI changes.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - LiveData is an observable data holder that allows components to observe and react to changes in the data. It ensures that UI updates are automatically triggered only when the observed data changes and when the UI component is in an active state.
    - [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - For managing app navigation.




## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/weather-app.git
