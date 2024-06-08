package codingtest.jatri.WeatherApp.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import codingtest.jatri.WeatherApp.R
import codingtest.jatri.WeatherApp.databinding.ActivityMainBinding
import codingtest.jatri.WeatherApp.utils.MyFirebaseMessagingService
import codingtest.jatri.WeatherApp.workers.WeatherWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyFirebaseMessagingService.createNotificationChannel(this)


        val workRequest = PeriodicWorkRequestBuilder<WeatherWorker>(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

    }
}