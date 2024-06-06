package codingtest.jatri.WeatherApp.api

import codingtest.jatri.WeatherApp.models.CityWeatherResponse
import codingtest.jatri.WeatherApp.models.WeatherDetailsResponse
import codingtest.jatri.WeatherApp.ui.CityLocationFragment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("find")
    suspend fun getCityWeatherList(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") cnt: Int,
        @Query("appid") appId: String,
    ): Response<CityWeatherResponse>

    @GET("weather")
    suspend fun getWeatherDetails(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
    ): Response<WeatherDetailsResponse>
}