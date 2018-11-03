package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

internal fun FragmentManager.removeFragment(tag: String) {

    this.beginTransaction()
            .disallowAddToBackStack()
            .remove(this.findFragmentByTag(tag))
            .commitNow()
}

internal fun FragmentManager.addFragment(containerViewId: Int,
                                         fragment: Fragment,
                                         tag: String) {
    this.beginTransaction()
            .disallowAddToBackStack()
            .add(containerViewId, fragment, tag)
            .commit()
}