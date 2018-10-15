package vkstatistic.apoyark.com.vkstatistics.ui.base.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import dagger.android.support.AndroidSupportInjection
import vkstatistic.apoyark.com.vkstatistics.utils.CommonUtil

abstract class BaseFragment : Fragment(), MVPView {

    private var parentActivity: BaseActivity? = null
    private var progressBar: ProgressBar? = null

    override fun showProgress() {
        hideProgress()
        progressBar = CommonUtil.showProgressBar(this.context!!)
    }

    override fun hideProgress() {
        if (progressBar != null) {
            progressBar?.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    fun getBaseActivity() = parentActivity

    abstract fun setUp()
}