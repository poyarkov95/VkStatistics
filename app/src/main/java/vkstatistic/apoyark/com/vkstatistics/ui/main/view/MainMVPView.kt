package vkstatistic.apoyark.com.vkstatistics.ui.main.view

import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface MainMVPView : MVPView {
    fun startSignIn()

    fun signedIn()
}