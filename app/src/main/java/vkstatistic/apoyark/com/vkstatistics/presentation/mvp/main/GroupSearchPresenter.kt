package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.CurrentUser
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.searchgroups.GroupSearchInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.DisposableManager
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class GroupSearchPresenter @Inject constructor(private val groupSearchInteractor: GroupSearchInteractor,
                                               private val disposableManager: DisposableManager, private val schedulerProvider: SchedulerProvider) : MvpPresenter<GroupSearchView>() {

    private var cachedSearchQuery: String = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (!CurrentUser.isAuthtorized()) {
            viewState.startSignIn()
        }
    }

    fun searchGroups(q: String) {
        viewState.showProgress()
        cachedSearchQuery = q
        disposableManager.add(
                groupSearchInteractor.searchGroups(q)
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe(this::onGroupsLoaded, this::onGroupsLoadError)
        )
    }

    private fun onGroupsLoaded(groupList: List<Group>) {
        if (groupList.isEmpty()) {
            viewState.showEmptyRecyclerView()
        } else {
            viewState.showSearchResult(groupList)
        }
    }

    private fun onGroupsLoadError(throwable: Throwable) {
        viewState.showErrorView()
        viewState.showErrorMessage(throwable.message)
    }

    fun retryLoad() {
        viewState.showProgress()
        searchGroups(cachedSearchQuery)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableManager.dispose()
    }
}