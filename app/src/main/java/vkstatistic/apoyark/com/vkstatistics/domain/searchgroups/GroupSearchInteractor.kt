package vkstatistic.apoyark.com.vkstatistics.domain.searchgroups

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

class GroupSearchInteractor @Inject constructor(private val groupRepository: GroupRepository, val schedulerProvider: SchedulerProvider) {

    fun searchGroups(q: String): Single<List<Group>> {
        return groupRepository.searchGroups(q)
                .subscribeOn(schedulerProvider.io())
    }
}
