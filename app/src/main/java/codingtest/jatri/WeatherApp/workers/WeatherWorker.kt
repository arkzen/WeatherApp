package codingtest.jatri.WeatherApp.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import codingtest.jatri.WeatherApp.repository.WeatherRepository
import codingtest.jatri.WeatherApp.utils.Constants
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class WeatherWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: WeatherRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {

            val lat = 23.68
            val lon = 90.35
            val apiKey = Constants.API_KEY

            val response = repository.getWeatherDetails(lat, lon, apiKey)
            if (response.isSuccessful) {
                val weather = response.body()
                weather?.let {
                    FirebaseMessaging.getInstance().send(
                        RemoteMessage.Builder("YOUR_SENDER_ID@fcm.googleapis.com")
                        .setMessageId(System.currentTimeMillis().toString())
                        .addData("title", "Daily Weather Update")
                        .addData("body", "Today's weather: ${it.main?.temp}°C")
                        .build())
                }
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}