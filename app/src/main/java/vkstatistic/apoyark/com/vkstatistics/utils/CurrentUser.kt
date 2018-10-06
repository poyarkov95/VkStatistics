package vkstatistic.apoyark.com.vkstatistics.utils

import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKSdk

object CurrentUser {

    fun getAccessToken(): String? {
        if (VKAccessToken.currentToken() == null) {
            return null
        }
        return VKAccessToken.currentToken().accessToken
    }

    fun getId(): String? {
        if (VKAccessToken.currentToken() == null) {
            return null
        }
        return VKAccessToken.currentToken().userId
    }

    fun isAuthtorized() : Boolean {
        return VKSdk.isLoggedIn()
        && VKAccessToken.currentToken() != null
        && !VKAccessToken.currentToken().isExpired
    }
}