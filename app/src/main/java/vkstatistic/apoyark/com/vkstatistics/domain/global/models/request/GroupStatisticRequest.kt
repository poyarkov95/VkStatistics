package vkstatistic.apoyark.com.vkstatistics.domain.global.models.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import vkstatistic.apoyark.com.vkstatistics.AppConstants

data class GroupStatisticRequest(@SerializedName(VKApiConst.GROUP_ID) private val groupId: String) : BaseRequestModel() {

    override fun onMapCreate(map: HashMap<String, String?>) {
        map[VKApiConst.GROUP_ID] = groupId
        map[AppConstants.STATISTIC_AGGREGATION_KEY] = AppConstants.STATISTIC_AGGREGATION_VALUE
        map["timestamp_from"] = "1483228800"
        map["timestamp_to"] = "1485907200"
        map["interval"] = "week"

        //1483228800
        //1485907200
    }
}