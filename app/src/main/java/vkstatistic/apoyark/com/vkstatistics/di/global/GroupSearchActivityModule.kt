package vkstatistic.apoyark.com.vkstatistics.di.global

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.domain.searchgroups.GroupSearchInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.adapters.SearchResultAdapter

@Module
class GroupSearchActivityModule {

    @Provides
    fun provideGroupSearchInteractor(groupRepository: GroupRepository, schedulerProvider: SchedulerProvider) = GroupSearchInteractor(groupRepository, schedulerProvider)

    @Provides
    fun provideSearchResultAdapter(): SearchResultAdapter = SearchResultAdapter(ArrayList())
}