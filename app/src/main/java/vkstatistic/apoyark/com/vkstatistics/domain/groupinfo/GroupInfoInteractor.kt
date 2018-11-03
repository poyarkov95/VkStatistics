package vkstatistic.apoyark.com.vkstatistics.domain.groupinfo

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

class GroupInfoInteractor @Inject constructor(private val groupRepository: GroupRepository, val schedulerProvider: SchedulerProvider) {

    fun findGroupById(groupId: String): Single<Group> {
        return groupRepository.findGroupById(groupId)
                .subscribeOn(schedulerProvider.io())
    }
}