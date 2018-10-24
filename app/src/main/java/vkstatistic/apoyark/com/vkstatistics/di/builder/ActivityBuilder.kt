package vkstatistic.apoyark.com.vkstatistics.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.GroupInfoFragmentProvider
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view.GroupInfoActivity
import vkstatistic.apoyark.com.vkstatistics.ui.main.MainActivityModule
import vkstatistic.apoyark.com.vkstatistics.ui.main.view.MainActivity
import vkstatistic.apoyark.com.vkstatistics.ui.search.SearchResultFragmentProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (SearchResultFragmentProvider::class)])
    abstract fun bindMainActibity(): MainActivity

    @ContributesAndroidInjector(modules = [(GroupInfoFragmentProvider::class)])
    abstract fun bindGroupInfoActivity(): GroupInfoActivity
}