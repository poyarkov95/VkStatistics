package vkstatistic.apoyark.com.vkstatistics.ui.main

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.ui.main.interactor.MainInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.main.interactor.MainMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.main.presenter.MainMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.main.presenter.MainPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.main.view.MainMVPView

@Module
class MainActivityModule {
    @Provides
    internal fun provideLoginInteractor(interactor: MainInteractor): MainMVPInteractor = interactor

    @Provides
    internal fun provideLoginPresenter(presenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = presenter
}