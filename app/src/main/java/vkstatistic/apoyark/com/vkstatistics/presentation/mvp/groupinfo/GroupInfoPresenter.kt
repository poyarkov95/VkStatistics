package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.groupinfo.GroupInfoInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class GroupInfoPresenter @Inject constructor(private val groupInfoInteractor: GroupInfoInteractor,
                                             private val compositeDisposable: CompositeDisposable,
                                             private val schedulerProvider: SchedulerProvider) :
        MvpPresenter<GroupInfoView>() {

    var cachedGroupId: Int = 0

    fun searchGroup(groupId: Int) {
        viewState.showProgress()
        cachedGroupId = groupId
        viewState.hideViewContent()
        compositeDisposable.add(
                groupInfoInteractor.findGroupById(groupId.toString())
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe(this::onGroupLoaded, this::onGroupLoadError)
        )
    }

    private fun onGroupLoaded(group: Group) {
        viewState.hideProgress()
        viewState.showViewContent()
        viewState.showGroup(group)
    }

    private fun onGroupLoadError(throwable: Throwable) {
        viewState.hideProgress()
        viewState.showErrorView()
        viewState.showErrorMessage(throwable.message)
    }

    fun retryLoad() {
        viewState.hideErrorView()
        viewState.showProgress()
        searchGroup(cachedGroupId)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}