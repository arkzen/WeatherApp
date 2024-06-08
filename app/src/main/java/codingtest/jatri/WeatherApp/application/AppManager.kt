package codingtest.jatri.WeatherApp.application

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import codingtest.jatri.WeatherApp.workers.HiltWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class AppManager  @Inject constructor(): Application() {

    init {
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }
    companion object {
        private var instance: AppManager? = null

        fun appContext(): AppManager {
            return instance as AppManager
        }
    }
}