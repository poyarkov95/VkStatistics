package vkstatistic.apoyark.com.vkstatistics.network.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst

data class GroupSearchRequest(@SerializedName(VKApiConst.Q) val search: String) : BaseRequestModel() {

    override fun onMapCreate(map: HashMap<String, String?>) {
        map[VKApiConst.Q] = search
        map[VKApiConst.SORT] = 0.toString()
    }
}