package vkstatistic.apoyark.com.vkstatistics.di.groupinfo

import dagger.Component
import vkstatistic.apoyark.com.vkstatistics.di.component.AppComponent
import vkstatistic.apoyark.com.vkstatistics.di.global.AppModule
import vkstatistic.apoyark.com.vkstatistics.di.global.DataModule
import vkstatistic.apoyark.com.vkstatistics.di.global.GroupInfoActivityModule
import vkstatistic.apoyark.com.vkstatistics.di.scope.Presenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo.GroupInfoPresenter

@Presenter
@Component(dependencies = [(AppComponent::class)], modules = [(GroupInfoActivityModule::class), (DataModule::class), (AppModule::class)])
interface GroupInfoComponent {

    fun getPresenter() : GroupInfoPresenter
}