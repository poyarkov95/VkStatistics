package vkstatistic.apoyark.com.vkstatistics.network.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import vkstatistic.apoyark.com.vkstatistics.network.response.GroupResponse
import vkstatistic.apoyark.com.vkstatistics.utils.ApiMethods

interface GroupApi {

    @GET(ApiMethods.SEARCH_GROUPS)
    fun getGroups(@QueryMap map: Map<String, String?>) : Observable<GroupResponse>
}