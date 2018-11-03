package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider {

    fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T> = ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun io() : Scheduler {
        return Schedulers.io()
    }

    fun mainThread() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}