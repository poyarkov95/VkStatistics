package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.presenter

import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.MVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor.GroupInfoMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view.GroupInfoMVPView

interface GroupInfoMVPPresenter<V : GroupInfoMVPView, I : GroupInfoMVPInteractor> : MVPPresenter<V, I> {

    fun searchGroup(groupId: Int?)
}