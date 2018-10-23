package vkstatistic.apoyark.com.vkstatistics.network.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.network.response.GroupResponse
import vkstatistic.apoyark.com.vkstatistics.utils.ApiMethods

interface GroupApi {

    @GET(ApiMethods.SEARCH_GROUPS)
    fun getGroups(@QueryMap map: Map<String, String?>) : Observable<GroupResponse<Group>>

    @GET(ApiMethods.SEARCH_MEMBERS_INFO)
    fun getMembersIds(@QueryMap map: Map<String, String?>) : Observable<GroupResponse<String>>
}