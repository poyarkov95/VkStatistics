package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.CurrentUser
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.searchgroups.GroupSearchInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class GroupSearchPresenter @Inject constructor(private val groupSearchInteractor: GroupSearchInteractor, private val compositeDisposable: CompositeDisposable, private val schedulerProvider: SchedulerProvider) : MvpPresenter<GroupSearchView>() {

    private var cachedSearchQuery: String = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (!CurrentUser.isAuthtorized()) {
            viewState.startSignIn()
        }
    }

    fun searchGroups(q: String) {
        viewState.showProgress()
        viewState.hideRecyclerView()
        cachedSearchQuery = q
        compositeDisposable.add(
                groupSearchInteractor.searchGroups(q)
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe(this::onGroupsLoaded, this::onGroupsLoadError)
        )
    }

    private fun onGroupsLoaded(groupList: List<Group>) {
        viewState.hideProgress()
        viewState.showSearchResult(groupList)
    }

    private fun onGroupsLoadError(throwable: Throwable) {
        viewState.hideProgress()
        viewState.showErrorView()
        viewState.showErrorMessage(throwable.message)
    }

    fun retryLoad() {
        viewState.hideErrorView()
        viewState.showProgress()
        searchGroups(cachedSearchQuery)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}