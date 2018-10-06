package vkstatistic.apoyark.com.vkstatistics.ui.base.presenter

import vkstatistic.apoyark.com.vkstatistics.ui.base.interactor.MVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface MVPBasePresenter<V : MVPView, I : MVPInteractor> {
    fun onAttach(view: V)

    fun onDetach()

    fun getView(): V?
}