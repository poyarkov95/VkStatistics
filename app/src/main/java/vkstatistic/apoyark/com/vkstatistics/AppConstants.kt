package vkstatistic.apoyark.com.vkstatistics

object AppConstants {
    const val VK_BASE_URL = "https://api.vk.com/method/"

    internal val DEFAULT_LOGIN_SCOPE : Array<String> = arrayOf()

    const val VK_API_VERSION = 5.87

    const val GROUP_IMAGE_URL_EXTRA = "GROUP_IMAGE_URL_EXTRA"

    const val GROUP_NAME_EXTRA = "GROUP_NAME_EXTRA"

    const val GROUP_ID_EXTRA = "GROUP_ID_EXTRA"

    const val ADDITIONAL_GROUP_INFO_FIELDS = "description,activity,start_date,crop_photo,members_count"

    const val SEARCH_GROUPS = "groups.search"

    const val FIND_GROUP_BY_ID = "groups.getById"

    const val GET_STATISTICS = "stats.get"

    const val STATISTIC_AGGREGATION_KEY = "stats_groups"

    const val STATISTIC_AGGREGATION_VALUE = "visitors"

    const val STATISTIC_INTERVAL_KEY = "interval"

    const val STATISTIC_INTERVAL_VALUE = "month"

    const val STATISTIC_TMSTMP_FROM_KEY = "timestamp_from"

    const val STATISTIC_TMSTMP_TO_KEY = "timestamp_to"

    const val CHART_TYPE_TAG = "CHART_TAG"

    const val CHART_MODEL = "CHART_MODEL"
}