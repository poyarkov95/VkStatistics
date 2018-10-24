package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view

import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface GroupInfoMVPView : MVPView {

    fun showGroup(group: Group?)

    fun showProgress()

    fun hideProgress()
}