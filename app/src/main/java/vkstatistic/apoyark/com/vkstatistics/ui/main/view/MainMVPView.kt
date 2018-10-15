package vkstatistic.apoyark.com.vkstatistics.ui.login.view

import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface MainMVPView : MVPView {
    fun startSignIn()

    fun signedIn()
}