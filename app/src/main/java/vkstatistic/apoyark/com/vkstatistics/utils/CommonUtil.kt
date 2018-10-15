package vkstatistic.apoyark.com.vkstatistics.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar

object CommonUtil {

    fun showProgressBar(context: Context) : ProgressBar {
        val progressBar = ProgressBar(context)
        progressBar.let {
            it.visibility = View.VISIBLE
            return it
        }
    }
}