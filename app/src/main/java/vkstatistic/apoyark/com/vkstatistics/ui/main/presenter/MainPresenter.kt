package vkstatistic.apoyark.com.vkstatistics.ui.login.presenter

import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.BasePresenter
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.MainMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.view.MainMVPView
import vkstatistic.apoyark.com.vkstatistics.utils.CurrentUser
import vkstatistic.apoyark.com.vkstatistics.utils.SchedulerProvider
import javax.inject.Inject

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), MainMVPPresenter<V, I> {

    override fun checkAuth() {
        if(!CurrentUser.isAuthtorized()) {
            getView()?.startSignIn()
        } else {
            getView()?.signedIn()
        }
    }
}