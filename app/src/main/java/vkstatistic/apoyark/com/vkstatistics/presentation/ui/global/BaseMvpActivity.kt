package vkstatistic.apoyark.com.vkstatistics.presentation.ui.global

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseMvpActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }

    private fun performDependencyInjection() = AndroidInjection.inject(this)
}