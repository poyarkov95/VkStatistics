package vkstatistic.apoyark.com.vkstatistics.network.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants

data class GroupRequest(@SerializedName(VKApiConst.GROUP_ID) private val groupId: String) : BaseRequestModel(){

    override fun onMapCreate(map: HashMap<String, String?>) {
        map[VKApiConst.GROUP_ID] = groupId
        map[VKApiConst.FIELDS] = ApiConstants.ADDITIONAL_GROUP_INFO_FIELDS
    }

}