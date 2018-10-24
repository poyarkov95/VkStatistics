package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor

import io.reactivex.Observable
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.network.response.group.GroupResponse
import vkstatistic.apoyark.com.vkstatistics.ui.base.interactor.MVPInteractor

interface GroupInfoMVPInteractor : MVPInteractor {

    fun findGroupById(groupId: String) : Observable<GroupResponse<Group>>
}