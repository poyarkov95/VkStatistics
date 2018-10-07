package vkstatistic.apoyark.com.vkstatistics.ui.login

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.LoginInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.LoginMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.presenter.LoginMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.login.presenter.LoginPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.login.view.LoginMVPView

@Module
class LoginActivityModule {
    @Provides
    internal fun provideLoginInteractor(interactor: LoginInteractor): LoginMVPInteractor = interactor

    @Provides
    internal fun provideLoginPresenter(presenter: LoginPresenter<LoginMVPView, LoginMVPInteractor>)
            : LoginMVPPresenter<LoginMVPView, LoginMVPInteractor> = presenter
}