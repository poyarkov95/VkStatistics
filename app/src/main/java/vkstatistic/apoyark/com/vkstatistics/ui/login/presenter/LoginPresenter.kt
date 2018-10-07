package vkstatistic.apoyark.com.vkstatistics.ui.login.presenter

import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.BasePresenter
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.LoginMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.view.LoginMVPView
import vkstatistic.apoyark.com.vkstatistics.utils.CurrentUser
import vkstatistic.apoyark.com.vkstatistics.utils.SchedulerProvider
import javax.inject.Inject

class LoginPresenter<V : LoginMVPView, I : LoginMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), LoginMVPPresenter<V, I> {

    override fun checkAuth() {
        if(!CurrentUser.isAuthtorized()) {
            getView()?.startSignIn()
        } else {
            getView()?.signedIn()
        }
    }
}