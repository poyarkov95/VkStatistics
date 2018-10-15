package vkstatistic.apoyark.com.vkstatistics.ui.search.presenter

import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.BasePresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultMVPView
import vkstatistic.apoyark.com.vkstatistics.utils.SchedulerProvider
import javax.inject.Inject

class SearchResultPresenter<V : SearchResultMVPView, I : SearchResultMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), SearchResultMVPPresenter<V, I> {

    override fun searchGroups(q: String) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(it.getGroupList(q)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe { groupResponse ->
                        getView()?.let {
                            it.showSearchResult(groupResponse.response.items)
                            it.hideProgress()
                        }
                    })
        }
    }
}