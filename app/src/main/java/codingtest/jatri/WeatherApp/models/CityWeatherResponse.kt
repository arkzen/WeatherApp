package codingtest.jatri.WeatherApp.models

import java.io.Serializable

data class CityWeatherResponse(
    val cod: String? = "",
    val count: Int? = 0,
    val list: List<CityList?>? = listOf(),
    val message: String? = ""

)

data class CityList(
    val clouds: Clouds? = Clouds(),
    val coord: Coord? = Coord(),
    val dt: Int? = 0,
    val id: Int? = 0,
    val main: Main? = Main(),
    val name: String? = "",
    val rain: Any? = Any(),
    val snow: Any? = Any(),
    val sys: Sys? = Sys(),
    val weather: List<Weather?>? = listOf(),
    val wind: Wind? = Wind()
) : Serializable

data class Clouds(
    val all: Int? = 0
)

data class Coord(
    val lat: Double? = 0.0,
    val lon: Double? = 0.0
)


data class Main(
    val feels_like: Double? = 0.0,
    val grnd_level: Int? = 0,
    val humidity: Int? = 0,
    val pressure: Int? = 0,
    val sea_level: Int? = 0,
    val temp: Double? = 0.0,
    val temp_max: Double? = 0.0,
    val temp_min: Double? = 0.0
)

data class Sys(
    val country: String? = "",
    val sunrise: Int? = 0,
    val sunset: Int? = 0
)

data class Weather(
    val description: String? = "",
    val icon: String? = "",
    val id: Int? = 0,
    val main: String? = ""
)

data class Wind(
    val deg: Int? = 0,
    val gust: Double? = 0.0,
    val speed: Double? = 0.0
)