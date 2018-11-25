package vkstatistic.apoyark.com.vkstatistics.data.repositories

import com.vk.sdk.api.VKError
import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.data.network.GroupApi
import vkstatistic.apoyark.com.vkstatistics.data.network.exceptions.NoPermissionsException
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.request.GroupInfoRequest
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.request.GroupSearchRequest
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.request.GroupStatisticRequest
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.Statistic
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(private val groupApi: GroupApi) : GroupRepository {
    override fun searchGroups(q: String): Single<List<Group>> {
        return groupApi.searchGroups(GroupSearchRequest(q).toMap())
                .flatMap { groupResponse -> Single.just(groupResponse.response.items) }
    }

    override fun findGroupById(groupId: String): Single<Group> {
        return groupApi.findGroupById(GroupInfoRequest(groupId).toMap())
                .flatMap { groupResponse -> Single.just(groupResponse.response.first()) }
    }

    override fun findGroupStatistic(groupId: String): Single<List<Statistic>> {
        return groupApi.findGroupStatistics(GroupStatisticRequest(groupId).toMap())
                .flatMap({
                    if (it.error != null) {
                        processApiError(it.error)
                    }
                    Single.just(it.response)
                })
    }

    private fun processApiError(vkError: VKError) {
        when (vkError.apiError.errorCode) {
            7 -> throw NoPermissionsException("No permissions error.")
        }
    }
}