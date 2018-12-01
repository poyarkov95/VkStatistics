package vkstatistic.apoyark.com.vkstatistics.di.groupsearch

import dagger.Component
import vkstatistic.apoyark.com.vkstatistics.di.component.AppComponent
import vkstatistic.apoyark.com.vkstatistics.di.global.AppModule
import vkstatistic.apoyark.com.vkstatistics.di.global.DataModule
import vkstatistic.apoyark.com.vkstatistics.di.global.GroupInfoActivityModule
import vkstatistic.apoyark.com.vkstatistics.di.global.HttpInterceptorsModule
import vkstatistic.apoyark.com.vkstatistics.di.global.scope.Presenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main.GroupSearchPresenter

@Presenter
@Component(dependencies = [(AppComponent::class)], modules = [(GroupInfoActivityModule::class), (DataModule::class), (AppModule::class), (HttpInterceptorsModule::class)])
interface GroupSearchComponent {

    fun getPresenter(): GroupSearchPresenter
}