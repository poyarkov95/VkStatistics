package vkstatistic.apoyark.com.vkstatistics.ui.login.presenter

import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.MVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.LoginMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.view.LoginMVPView

interface LoginMVPPresenter<V : LoginMVPView, I : LoginMVPInteractor> : MVPPresenter<V, I> {
    fun checkAuth()
}