package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor

import io.reactivex.Observable
import vkstatistic.apoyark.com.vkstatistics.network.api.GroupApi
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.network.request.GroupRequest
import vkstatistic.apoyark.com.vkstatistics.network.response.group.GroupResponse
import javax.inject.Inject

class GroupInfoInteractor @Inject constructor() : GroupInfoMVPInteractor {

    @Inject
    lateinit var groupApi: GroupApi

    override fun findGroupById(groupId: String): Observable<GroupResponse<Group>> = groupApi.findGroupById(GroupRequest(groupId).toMap())
}