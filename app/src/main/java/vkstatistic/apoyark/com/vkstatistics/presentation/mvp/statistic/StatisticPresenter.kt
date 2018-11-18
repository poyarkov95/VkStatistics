package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.domain.statistic.StatisticInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class StatisticPresenter @Inject constructor(private val statisticInteractor: StatisticInteractor,
                                             private val compositeDisposable: CompositeDisposable,
                                             private val schedulerProvider: SchedulerProvider)
    : MvpPresenter<StatisticView>() {

    private var cachedGroupId: Int = 0

    fun findGroupStatistic(groupId: Int) {
        viewState.showProgress()
        cachedGroupId = groupId
        viewState.hideViewContent()
        compositeDisposable.add(
                statisticInteractor.findGroupStatistic(groupId.toString())
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe(this::onStatisticLoaded, this::onStatisticLoadError)
        )
    }

    private fun onStatisticLoaded(statisticModel: StatisticModel) {
        viewState.hideProgress()
        viewState.showViewContent()
        viewState.showStatistics(statisticModel)
    }

    private fun onStatisticLoadError(throwable: Throwable) {
        viewState.hideProgress()
        viewState.showErrorView()
        viewState.showErrorMessage(throwable.message)
    }

    fun retryLoad() {
        viewState.hideErrorView()
        viewState.showProgress()
        findGroupStatistic(cachedGroupId)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}