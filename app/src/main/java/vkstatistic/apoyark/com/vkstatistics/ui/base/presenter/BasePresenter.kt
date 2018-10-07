package vkstatistic.apoyark.com.vkstatistics.ui.base.presenter

import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.ui.base.interactor.MVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView
import vkstatistic.apoyark.com.vkstatistics.utils.SchedulerProvider

abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(protected var interactor: I?, protected var schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V, I> {
    private var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun getView(): V? {
        return view
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }
}