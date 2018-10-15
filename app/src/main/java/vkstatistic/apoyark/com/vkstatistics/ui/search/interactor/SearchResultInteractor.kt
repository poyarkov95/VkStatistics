package vkstatistic.apoyark.com.vkstatistics.ui.search.interactor

import io.reactivex.Observable
import vkstatistic.apoyark.com.vkstatistics.network.api.GroupApi
import vkstatistic.apoyark.com.vkstatistics.network.request.GroupRequest
import vkstatistic.apoyark.com.vkstatistics.network.response.GroupResponse
import javax.inject.Inject

class SearchResultInteractor @Inject constructor() : SearchResultMVPInteractor {

    @Inject
    lateinit var groupApi: GroupApi

    override fun getGroupList(q: String): Observable<GroupResponse> = groupApi.getGroups(GroupRequest(q).toMap())

}