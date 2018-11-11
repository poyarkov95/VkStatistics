package vkstatistic.apoyark.com.vkstatistics

object AppConstants {
    const val VK_BASE_URL = "https://api.vk.com/method/"

    internal val DEFAULT_LOGIN_SCOPE : Array<String> = arrayOf()

    const val DEFAULT_VERSION = 5.87

    const val GROUP_EXTRA = "GROUP_EXTRA"

    const val GROUP_IMAGE_URL_EXTRA = "GROUP_IMAGE_URL_EXTRA"

    const val GROUP_NAME_EXTRA = "GROUP_NAME_EXTRA"

    const val GROUP_ID_EXTRA = "GROUP_ID_EXTRA"

    const val ADDITIONAL_GROUP_INFO_FIELDS = "description,activity,start_date,crop_photo,members_count"

    const val SEARCH_GROUPS = "groups.search"

    const val FIND_GROUP_BY_ID = "groups.getById"

    const val GET_STATISTICS = "stats.get"

    const val STATISTIC_AGGREGATION_KEY = "stats_groups"

    const val STATISTIC_AGGREGATION_VALUE = "visitors"
}