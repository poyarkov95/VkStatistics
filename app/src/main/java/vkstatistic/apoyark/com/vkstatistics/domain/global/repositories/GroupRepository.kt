package vkstatistic.apoyark.com.vkstatistics.domain.global.repositories

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.Statistic

interface GroupRepository {

    fun searchGroups(q: String) : Single<List<Group>>

    fun findGroupById(groupId: String) : Single<Group>

    fun findGroupStatistic(groupId: String) : Single<Statistic>
}