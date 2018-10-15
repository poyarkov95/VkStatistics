package vkstatistic.apoyark.com.vkstatistics.ui.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import dagger.android.AndroidInjection
import vkstatistic.apoyark.com.vkstatistics.utils.CommonUtil

abstract class BaseActivity : AppCompatActivity(), MVPView {
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }

    private fun performDependencyInjection() = AndroidInjection.inject(this)

    override fun showProgress() {
        hideProgress()
        progressBar = CommonUtil.showProgressBar(this)
    }

    override fun hideProgress() {
        if (progressBar != null) {
            progressBar?.visibility = View.GONE
        }
    }
}