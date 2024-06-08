package codingtest.jatri.WeatherApp.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import codingtest.jatri.WeatherApp.R
import codingtest.jatri.WeatherApp.adapter.AdapterWeatherList
import codingtest.jatri.WeatherApp.databinding.FragmentCityListBinding
import codingtest.jatri.WeatherApp.models.CityList
import codingtest.jatri.WeatherApp.utils.LocationHelper
import codingtest.jatri.WeatherApp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CityListFragment : Fragment(R.layout.fragment_city_list) {

    @Inject
    lateinit var locationHelper: LocationHelper

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var weatherAdapter: AdapterWeatherList
    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        val view = binding.root

        getCurrentLocation()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvWeatherPlaceList.layoutManager = LinearLayoutManager(requireContext())
        weatherAdapter = AdapterWeatherList {
            cityItemClicked(it)
        }
        binding.rvWeatherPlaceList.adapter = weatherAdapter

        weatherViewModel.responseTvShow.observe(viewLifecycleOwner) { cityList ->
            weatherAdapter.updateCityList(cityList)
        }


    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }


        locationHelper.getCurrentLocation { latitude, longitude ->
            weatherViewModel.getCityWeather(latitude, longitude)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission was granted
                getCurrentLocation()
            } else {
                // Permission denied
            }
        }
    }

    fun cityItemClicked(cityItem: CityList) {
        val action =
            CityListFragmentDirections.actionCityListFragmentToCityLocationFragment(cityItem)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}