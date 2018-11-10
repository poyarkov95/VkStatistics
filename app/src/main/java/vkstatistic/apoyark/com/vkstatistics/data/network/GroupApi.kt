package vkstatistic.apoyark.com.vkstatistics.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.response.GroupResponse
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.response.SearchGroupResponse
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.response.StatisticResponse
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.Statistic

interface GroupApi {

    @GET(AppConstants.SEARCH_GROUPS)
    fun searchGroups(@QueryMap map: Map<String, String?>): Single<SearchGroupResponse<Group>>

    @GET(AppConstants.FIND_GROUP_BY_ID)
    fun findGroupById(@QueryMap map: Map<String, String?>): Single<GroupResponse<Group>>

    @GET(AppConstants.GET_STATISTICS)
    fun getStatistics(@QueryMap map: Map<String, String?>): Single<StatisticResponse<Statistic>>
}