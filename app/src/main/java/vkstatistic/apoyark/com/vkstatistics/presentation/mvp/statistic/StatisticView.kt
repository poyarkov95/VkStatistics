package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface StatisticView : MvpView {

    fun showStatistics(statisticModel: StatisticModel)

    fun showViewContent()

    fun hideViewContent()

    fun showProgress()

    fun hideProgress()

    fun showErrorView()

    fun hideErrorView()

    fun showErrorMessage(message: String?)
}