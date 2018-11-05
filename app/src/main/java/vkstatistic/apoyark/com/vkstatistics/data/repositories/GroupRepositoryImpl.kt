package vkstatistic.apoyark.com.vkstatistics.data.repositories

import io.reactivex.Observable
import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.data.network.GroupApi
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.request.GroupInfoRequest
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.request.GroupSearchRequest
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(private val groupApi: GroupApi) : GroupRepository {

    override fun searchGroups(q: String): Single<List<Group>> {
        return groupApi.searchGroups(GroupSearchRequest(q).toMap())
                .flatMap { groupResponse -> Single.just(groupResponse.response.items) }
    }

    override fun findGroupById(groupId: String): Single<Group> {
        return groupApi.findGroupById(GroupInfoRequest(groupId).toMap())
                .flatMap { groupResponse -> Single.just(groupResponse.response!!.first()) }
    }
}