package vkstatistic.apoyark.com.vkstatistics.di.statistic

import dagger.Component
import vkstatistic.apoyark.com.vkstatistics.di.component.AppComponent
import vkstatistic.apoyark.com.vkstatistics.di.global.AppModule
import vkstatistic.apoyark.com.vkstatistics.di.global.DataModule
import vkstatistic.apoyark.com.vkstatistics.di.global.HttpInterceptorsModule
import vkstatistic.apoyark.com.vkstatistics.di.global.StatisticActivityModule
import vkstatistic.apoyark.com.vkstatistics.di.global.scope.Presenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticPresenter

@Presenter
@Component(dependencies = [(AppComponent::class)], modules = [(StatisticActivityModule::class), (DataModule::class), (AppModule::class), (HttpInterceptorsModule::class)])
interface StatisticComponent {

    fun getPresenter(): StatisticPresenter
}