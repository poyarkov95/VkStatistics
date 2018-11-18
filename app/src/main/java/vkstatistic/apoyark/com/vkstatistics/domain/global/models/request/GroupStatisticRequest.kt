package vkstatistic.apoyark.com.vkstatistics.domain.global.models.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import java.util.*


data class GroupStatisticRequest(@SerializedName(VKApiConst.GROUP_ID) private val groupId: String) : BaseRequestModel() {

    override fun onMapCreate(map: HashMap<String, String?>) {
        map[VKApiConst.GROUP_ID] = groupId
        map[AppConstants.STATISTIC_AGGREGATION_KEY] = AppConstants.STATISTIC_AGGREGATION_VALUE
        map[AppConstants.STATISTIC_INTERVAL_VALUE] = AppConstants.STATISTIC_INTERVAL_VALUE
        map[AppConstants.STATISTIC_TMSTMP_FROM_KEY] = beginningOfYear()
        map[AppConstants.STATISTIC_TMSTMP_TO_KEY] = (DateTime().millis / 1000).toString()
        map[AppConstants.STATISTIC_INTERVAL_KEY] = AppConstants.STATISTIC_INTERVAL_VALUE
    }

    private fun beginningOfYear(): String {
        val firstDayOfYear = DateTime(LocalDateTime.now().year, 1, 1, 1, 1)
        return (firstDayOfYear.millis / 1000).toString()
    }
}