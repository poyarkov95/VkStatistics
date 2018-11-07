package vkstatistic.apoyark.com.vkstatistics.domain.global.models.group

import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R

class GroupPrivacyConverter {
    //todo injected context should be used here
    internal fun getByCode(code: Int): String {
        when (code) {
            0 -> return MyApplication.applicationContext().getString(R.string.opened_group)
            1 -> return MyApplication.applicationContext().getString(R.string.closed_group)
            2 -> return MyApplication.applicationContext().getString(R.string.private_group)
        }
        throw IllegalArgumentException("Unknown group privacy code: $code.")
    }
}