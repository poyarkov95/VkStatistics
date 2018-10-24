package vkstatistic.apoyark.com.vkstatistics.ui.search.interactor

import io.reactivex.Observable
import vkstatistic.apoyark.com.vkstatistics.network.api.GroupApi
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.network.request.GroupSearchRequest
import vkstatistic.apoyark.com.vkstatistics.network.response.searchgroup.SearchGroupResponse
import javax.inject.Inject

class SearchResultInteractor @Inject constructor() : SearchResultMVPInteractor {
    @Inject
    lateinit var groupApi: GroupApi

    override fun getGroupList(q: String): Observable<SearchGroupResponse<Group>> = groupApi.getGroups(GroupSearchRequest(q).toMap())
}