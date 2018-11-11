package vkstatistic.apoyark.com.vkstatistics.di.global

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.domain.statistic.StatisticInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider

@Module
class StatisticActivityModule {

    @Provides
    fun provideStatisticInteractor(groupRepository: GroupRepository, schedulerProvider: SchedulerProvider)
            = StatisticInteractor(groupRepository, schedulerProvider)
}