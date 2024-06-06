package codingtest.jatri.WeatherApp.repository

import codingtest.jatri.WeatherApp.api.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCityWeatherList(latitude: Double,longitude: Double,cnt: Int,appId: String)=apiService.getCityWeatherList(latitude,longitude,cnt,appId)
    suspend fun getWeatherDetails(latitude: Double,longitude: Double,appId: String)=apiService.getWeatherDetails(latitude,longitude,appId)
}