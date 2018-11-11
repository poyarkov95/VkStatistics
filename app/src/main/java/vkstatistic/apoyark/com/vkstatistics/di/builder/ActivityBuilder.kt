package vkstatistic.apoyark.com.vkstatistics.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.di.global.GroupInfoActivityModule
import vkstatistic.apoyark.com.vkstatistics.di.global.GroupSearchActivityModule
import vkstatistic.apoyark.com.vkstatistics.di.global.StatisticActivityModule
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupinfo.GroupInfoActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupsearch.GroupSearchActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic.StatisticActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(GroupSearchActivityModule::class)])
    abstract fun bindGroupSearchActibity(): GroupSearchActivity

    @ContributesAndroidInjector(modules = [(GroupInfoActivityModule::class)])
    abstract fun bindGroupInfoActivity(): GroupInfoActivity

    @ContributesAndroidInjector(modules = [(StatisticActivityModule::class)])
    abstract fun bindPieChartFragment(): StatisticActivity
}