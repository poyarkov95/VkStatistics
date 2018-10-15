package vkstatistic.apoyark.com.vkstatistics.ui.main.presenter

import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.MVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.main.interactor.MainMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.main.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
    fun checkAuth()
}