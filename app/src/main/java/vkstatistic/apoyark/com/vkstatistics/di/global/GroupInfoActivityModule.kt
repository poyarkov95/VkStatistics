package vkstatistic.apoyark.com.vkstatistics.di.global

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.domain.groupinfo.GroupInfoInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider

@Module
class GroupInfoActivityModule {

    @Provides
    fun provideGroupInfoInteractor(groupRepository: GroupRepository, schedulerProvider: SchedulerProvider) = GroupInfoInteractor(groupRepository, schedulerProvider)
}