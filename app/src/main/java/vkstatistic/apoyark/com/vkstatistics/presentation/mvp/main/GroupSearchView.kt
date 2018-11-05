package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.Group

@StateStrategyType(AddToEndSingleStrategy::class)
interface GroupSearchView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startSignIn()

    fun showSearchResult(searchResult: List<Group>?)

    fun hideRecyclerView()

    fun showProgress()

    fun hideProgress()

    fun showErrorView()

    fun hideErrorView()

    fun showErrorMessage(message: String?)
}