package codingtest.jatri.WeatherApp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import codingtest.jatri.WeatherApp.ui.CityListFragment
import codingtest.jatri.WeatherApp.ui.CityLocationFragment
import javax.inject.Inject

class AppFragmentFactory @Inject constructor(

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            CityListFragment::class.java.name -> {
                CityListFragment()
            }
            CityLocationFragment::class.java.name -> {
                CityLocationFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}