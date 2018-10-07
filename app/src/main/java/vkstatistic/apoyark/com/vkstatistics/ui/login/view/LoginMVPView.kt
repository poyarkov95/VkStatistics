package vkstatistic.apoyark.com.vkstatistics.ui.login.view

import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface LoginMVPView : MVPView {
    fun startSignIn()

    fun signedIn()
}