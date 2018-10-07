package vkstatistic.apoyark.com.vkstatistics.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.ui.login.LoginActivityModule
import vkstatistic.apoyark.com.vkstatistics.ui.login.view.LoginActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindMainActibity() : LoginActivity
}