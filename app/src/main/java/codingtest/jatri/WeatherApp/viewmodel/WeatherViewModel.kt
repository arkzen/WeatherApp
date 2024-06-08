package codingtest.jatri.WeatherApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import codingtest.jatri.WeatherApp.models.CityList
import codingtest.jatri.WeatherApp.repository.WeatherRepository
import codingtest.jatri.WeatherApp.utils.Constants
import codingtest.jatri.WeatherApp.utils.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationHelper: LocationHelper
) : ViewModel() {

    private val _response = MutableLiveData<List<CityList>>()
    val responseTvShow: LiveData<List<CityList>>
        get() = _response

    init {
        fetchAndFetchWeather()

    }

    private fun fetchAndFetchWeather() {
        locationHelper.getCurrentLocation { latitude, longitude ->
            getCityWeather(latitude, longitude)
        }
    }

     fun getCityWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        repository.getCityWeatherList(latitude, longitude, Constants.CNT, Constants.API_KEY).let { response ->

            if (response.isSuccessful) {
                val nonNullCityList = response.body()!!.list!!.filterNotNull()
                _response.postValue(nonNullCityList)
            } else {
                // error
            }
        }
    }

}
