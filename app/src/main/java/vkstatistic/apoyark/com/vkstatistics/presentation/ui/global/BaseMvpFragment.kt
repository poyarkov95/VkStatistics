package vkstatistic.apoyark.com.vkstatistics.presentation.ui.global

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import dagger.android.support.AndroidSupportInjection

abstract class BaseMvpFragment : Fragment() {

    private var parentActivity: BaseMvpActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    fun getBaseActivity() = parentActivity

    abstract fun setUp()
}