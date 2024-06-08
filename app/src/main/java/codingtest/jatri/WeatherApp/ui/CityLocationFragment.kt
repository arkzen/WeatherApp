package codingtest.jatri.WeatherApp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import codingtest.jatri.WeatherApp.R
import codingtest.jatri.WeatherApp.databinding.FragmentCityLocationBinding
import codingtest.jatri.WeatherApp.models.CityList
import codingtest.jatri.WeatherApp.utils.Constants
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CityLocationFragment : Fragment(R.layout.fragment_city_location) {
    private val args: CityLocationFragmentArgs by navArgs()
    private var _binding: FragmentCityLocationBinding? = null
    private lateinit var cityItem: CityList
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityItem = args.cityItem
        setupUI()
        showLocationOnMarker()
    }


    fun setupUI() {
        _binding?.containerTop?.imgBack?.visibility = View.VISIBLE
        _binding?.containerTop?.imgBack?.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        try {
            _binding?.tvCityName?.text = cityItem.name
            _binding?.tvHumidity?.text = cityItem.main?.humidity.toString()
            _binding?.tvWeatherStatus?.text = cityItem.weather?.get(0)?.main ?: ""
            _binding?.tvMaxTemp?.text = String.format(
                "%d°C", (cityItem.main?.temp_max?.minus(
                    Constants.TEMPERATURE_IN_KELVIN
                ))?.toInt()
            )
            _binding?.tvMinTemp?.text = String.format(
                "%d°C", (cityItem.main?.temp_min?.minus(
                    Constants.TEMPERATURE_IN_KELVIN
                ))?.toInt()
            )

            _binding?.tvTemp?.text = String.format(
                "%d°C",
                (cityItem.main?.temp?.minus(Constants.TEMPERATURE_IN_KELVIN))?.toInt()
            )

            _binding?.tvWindSpeed?.text = cityItem.wind?.speed.toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun showLocationOnMarker() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment

        mapFragment?.getMapAsync { googleMap ->
            val lat = cityItem.coord?.lat ?: 0.0
            val lon = cityItem.coord?.lon ?: 0.0
            val cityName = cityItem.name

            val markerOptions = MarkerOptions()
                .position(LatLng(lat, lon))
                .title(cityName)

            val marker = googleMap.addMarker(markerOptions)
            marker?.tag = cityName

            zoomToUserLocation(googleMap, lat, lon)

            googleMap.setOnMarkerClickListener { marker ->
                marker.showInfoWindow()
                true
            }
        }
    }

    private fun zoomToUserLocation(
        googleMap: GoogleMap,
        userLat: Double,
        userLon: Double
    ) {
        googleMap.setOnMapLoadedCallback {
            val latLng = LatLng(userLat, userLon)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}