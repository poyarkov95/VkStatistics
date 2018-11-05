package vkstatistic.apoyark.com.vkstatistics.domain.global.repositories

import io.reactivex.Observable
import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.Group

interface GroupRepository {

    fun searchGroups(q: String) : Single<List<Group>>

    fun findGroupById(groupId: String) : Single<Group>
}