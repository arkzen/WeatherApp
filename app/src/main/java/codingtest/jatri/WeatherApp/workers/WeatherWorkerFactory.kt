package codingtest.jatri.WeatherApp.workers

import android.content.Context
import androidx.work.WorkerParameters
import dagger.assisted.AssistedFactory

@AssistedFactory
interface WeatherWorkerFactory : ChildWorkerFactory {
    override fun create(appContext: Context, params: WorkerParameters): WeatherWorker
}
