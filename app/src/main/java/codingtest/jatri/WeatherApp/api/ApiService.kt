package codingtest.jatri.WeatherApp.api

import codingtest.jatri.WeatherApp.models.CityWeatherResponse
import codingtest.jatri.WeatherApp.ui.CityLocationFragment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("course/enroll-courses/")
    suspend fun getCityWeatherList(
        @Query("status") status: String
    ): Response<CityWeatherResponse>
}