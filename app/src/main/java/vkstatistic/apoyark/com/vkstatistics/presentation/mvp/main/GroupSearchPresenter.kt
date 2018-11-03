package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main

import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.CurrentUser
import vkstatistic.apoyark.com.vkstatistics.domain.searchgroups.GroupSearchInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.BasePresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class GroupSearchPresenter @Inject constructor(private val groupSearchInteractor: GroupSearchInteractor, private val compositeDisposable: CompositeDisposable, private val schedulerProvider: SchedulerProvider) : BasePresenter<GroupSearchView>(compositeDisposable) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (!CurrentUser.isAuthtorized()) {
            viewState.startSignIn()
        }
    }

    fun searchGroups(q: String) {
        viewState.showProgress()
        compositeDisposable.add(
                groupSearchInteractor.searchGroups(q)
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe { groupList ->
                            viewState.hideProgress()
                            viewState.showSearchResult(groupList)
                        }
        )
    }
}