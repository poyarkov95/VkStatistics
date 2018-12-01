package vkstatistic.apoyark.com.vkstatistics.domain.global.models.group

import android.content.Context
import vkstatistic.apoyark.com.vkstatistics.R

class GroupPrivacyConverter(private val context: Context) {
    internal fun getByCode(code: Int): String {
        when (code) {
            0 -> return context.getString(R.string.opened_group)
            1 -> return context.getString(R.string.closed_group)
            2 -> return context.getString(R.string.private_group)
        }
        throw IllegalArgumentException("Unknown group privacy code: $code.")
    }
}