package vkstatistic.apoyark.com.vkstatistics.network.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst

data class GroupRequest(@SerializedName(VKApiConst.GROUP_ID) private val groupId: String) : BaseRequestModel(){

    override fun onMapCreate(map: HashMap<String, String?>) {
        map[VKApiConst.GROUP_ID] = groupId
    }

}