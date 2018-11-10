package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.domain.statistic.StatisticInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class ChartPresenter @Inject constructor(private val statisticInteractor: StatisticInteractor, private val compositeDisposable: CompositeDisposable, private val schedulerProvider: SchedulerProvider) : MvpPresenter<ChartView>() {


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}