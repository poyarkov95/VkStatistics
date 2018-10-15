package vkstatistic.apoyark.com.vkstatistics.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.ui.main.MainActivityModule
import vkstatistic.apoyark.com.vkstatistics.ui.main.view.MainActivity
import vkstatistic.apoyark.com.vkstatistics.ui.search.SearchResultFragmentProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (SearchResultFragmentProvider::class)])
    abstract fun bindMainActibity(): MainActivity
}