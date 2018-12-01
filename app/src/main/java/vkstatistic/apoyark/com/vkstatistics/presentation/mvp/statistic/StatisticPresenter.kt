package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import vkstatistic.apoyark.com.vkstatistics.data.network.exceptions.NoNetworkConnectionException
import vkstatistic.apoyark.com.vkstatistics.data.network.exceptions.NoPermissionsException
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.domain.statistic.StatisticInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.DisposableManager
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class StatisticPresenter @Inject constructor(private val statisticInteractor: StatisticInteractor,
                                             private val disposableManager: DisposableManager,
                                             private val schedulerProvider: SchedulerProvider)
    : MvpPresenter<StatisticView>() {

    private var cachedGroupId: Int = 0

    fun findGroupStatistic(groupId: Int) {
        viewState.showProgress()
        cachedGroupId = groupId
        disposableManager.add(
                statisticInteractor.findGroupStatistic(groupId.toString())
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe(this::onStatisticLoaded, this::onStatisticLoadError)
        )
    }

    private fun onStatisticLoaded(statisticModel: StatisticModel) {
        viewState.showViewContent()
        viewState.showStatistics(statisticModel)
    }

    private fun onStatisticLoadError(throwable: Throwable) {
        if (throwable is NoPermissionsException) {
            viewState.showNoPermissionsView()
        }
        if (throwable is NoNetworkConnectionException) {
            viewState.showNetworkErrorView()
        }
        viewState.showErrorMessage(throwable.message)
    }

    fun retryLoad() {
        viewState.showProgress()
        findGroupStatistic(cachedGroupId)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableManager.dispose()
    }
}