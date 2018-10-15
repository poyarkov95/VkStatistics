package vkstatistic.apoyark.com.vkstatistics.network.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants
import vkstatistic.apoyark.com.vkstatistics.utils.CurrentUser

abstract class BaseRequestModel(@SerializedName(VKApiConst.VERSION)
                                val version: Double = ApiConstants.DEFAULT_VERSION,

                                @SerializedName(VKApiConst.ACCESS_TOKEN)
                                val accessToken: String? = CurrentUser.getAccessToken()) {

    fun toMap(): Map<String, String?> {
        val map: HashMap<String, String?> = HashMap()
        map[VKApiConst.VERSION] = version.toString()
        map[VKApiConst.ACCESS_TOKEN] = accessToken
        onMapCreate(map)
        return map
    }

    abstract fun onMapCreate(map: HashMap<String, String?>)
}