package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.groupinfo.GroupInfoInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.DisposableManager
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class GroupInfoPresenter @Inject constructor(private val groupInfoInteractor: GroupInfoInteractor,
                                             private val disposableManager: DisposableManager,
                                             private val schedulerProvider: SchedulerProvider)
    : MvpPresenter<GroupInfoView>() {

    var cachedGroupId: Int = 0

    fun searchGroup(groupId: Int) {
        viewState.showProgress()
        cachedGroupId = groupId
        disposableManager.add(
                groupInfoInteractor.findGroupById(groupId.toString())
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe(this::onGroupLoaded, this::onGroupLoadError)
        )
    }

    private fun onGroupLoaded(group: Group) {
        viewState.showViewContent()
        viewState.showGroup(group)
    }

    private fun onGroupLoadError(throwable: Throwable) {
        viewState.showErrorView()
        viewState.showErrorMessage(throwable.message)
    }

    fun retryLoad() {
        viewState.showProgress()
        searchGroup(cachedGroupId)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableManager.dispose()
    }
}