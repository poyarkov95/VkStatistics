package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.Group

@StateStrategyType(AddToEndSingleStrategy::class)
interface GroupInfoView : MvpView {

    fun showGroup(group: Group?)

    fun showProgress()

    fun hideProgress()
}