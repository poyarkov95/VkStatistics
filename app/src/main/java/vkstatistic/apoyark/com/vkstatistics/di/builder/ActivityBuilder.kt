package vkstatistic.apoyark.com.vkstatistics.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.di.global.GroupInfoActivityModule
import vkstatistic.apoyark.com.vkstatistics.di.global.GroupSearchActivityModule
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupinfo.GroupInfoActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupsearch.GroupSearchActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(GroupSearchActivityModule::class)])
    abstract fun bindGroupSearchActibity(): GroupSearchActivity

    @ContributesAndroidInjector(modules = [(GroupInfoActivityModule::class)])
    abstract fun bindGroupInfoActivity(): GroupInfoActivity
}