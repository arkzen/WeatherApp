package codingtest.jatri.WeatherApp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codingtest.jatri.WeatherApp.databinding.WeatherPlaceItemBinding
import codingtest.jatri.WeatherApp.models.CityList
import codingtest.jatri.WeatherApp.utils.Constants


class AdapterWeatherList(val adapterOnClick: (cityItem:CityList) -> Unit): RecyclerView.Adapter<AdapterWeatherList.WeatherViewHolder>() {

    private var weatherList = ArrayList<CityList>(emptyList())
    class WeatherViewHolder(val binding: WeatherPlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            WeatherPlaceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        val weatherItem = weatherList[position]
        holder.binding.apply {
            tvWeatherLocation.text = weatherItem.name
          try {
              tvWeatherStatus.text = weatherItem.weather?.get(0)?.description ?: ""
          } catch (e:Exception){
              e.printStackTrace()
          }
            tvProductCount.text = String.format("%d°C", (weatherItem.main?.temp?.minus(Constants.TEMPERATURE_IN_KELVIN))?.toInt())

            root.setOnClickListener {
                adapterOnClick.invoke(weatherItem)
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCityList(cityWeatherList: List< CityList>) {
        for (item in cityWeatherList) {
            weatherList.add(item)
        }
        notifyDataSetChanged()
    }

}