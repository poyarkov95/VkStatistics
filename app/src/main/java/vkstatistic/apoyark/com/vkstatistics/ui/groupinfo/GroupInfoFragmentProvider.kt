package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.di.module.NetworkModule
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view.GroupInfoFragment

@Module
abstract class GroupInfoFragmentProvider {

    @ContributesAndroidInjector(modules = [(GroupInfoFragmentModule::class), (NetworkModule::class)])
    internal abstract fun provideGroupInfoFragmentModule() : GroupInfoFragment
}