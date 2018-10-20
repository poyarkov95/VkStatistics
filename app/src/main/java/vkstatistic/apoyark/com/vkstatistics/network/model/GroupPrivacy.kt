package vkstatistic.apoyark.com.vkstatistics.network.model

import android.content.res.Resources
import vkstatistic.apoyark.com.vkstatistics.R

enum class GroupPrivacy(val code: Int, val privacyName: String) {
    CLOSED(0, Resources.getSystem().getString(R.string.closed_group)),
    OPEN(1, Resources.getSystem().getString(R.string.opened_group)),
    PRIVATE(2, Resources.getSystem().getString(R.string.private_group));

    companion object {
        internal fun getByCode(code: Int) : GroupPrivacy {
            when(code) {
                0 -> return CLOSED
                1 -> return OPEN
                2 -> return PRIVATE
            }
            throw IllegalArgumentException("Unknown group privacy code: $code.")
        }
    }

}