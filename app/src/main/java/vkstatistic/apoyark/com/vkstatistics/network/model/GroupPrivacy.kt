package vkstatistic.apoyark.com.vkstatistics.network.model

import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R

enum class GroupPrivacy(val code: Int, private val stringResourceCode: Int) {
    OPEN(0, R.string.opened_group),
    CLOSED(1, R.string.closed_group),
    PRIVATE(2, R.string.private_group);

    companion object {
        internal fun getByCode(code: Int) : GroupPrivacy {
            when(code) {
                0 -> return OPEN
                1 -> return CLOSED
                2 -> return PRIVATE
            }
            throw IllegalArgumentException("Unknown group privacy code: $code.")
        }
    }

    override fun toString(): String {
        return MyApplication.applicationContext().getString(stringResourceCode)
    }

}