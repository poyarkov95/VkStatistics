package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MvpView> internal constructor(private val compositeDisposable: CompositeDisposable) : MvpPresenter<V>() {

    fun onDetach() {
        compositeDisposable.dispose()
    }
}