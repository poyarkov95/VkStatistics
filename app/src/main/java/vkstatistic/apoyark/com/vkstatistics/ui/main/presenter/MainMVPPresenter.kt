package vkstatistic.apoyark.com.vkstatistics.ui.login.presenter

import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.MVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.MainMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
    fun checkAuth()
}