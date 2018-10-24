package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.presenter

import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.BasePresenter
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor.GroupInfoMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view.GroupInfoMVPView
import vkstatistic.apoyark.com.vkstatistics.utils.SchedulerProvider
import javax.inject.Inject

class GroupInfoPresenter<V : GroupInfoMVPView, I : GroupInfoMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), GroupInfoMVPPresenter<V, I> {

    override fun showGroup(groupId: Int) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                    it.findGroupById(groupId.toString())
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe {groupResponse ->
                        getView()?.showGroup(groupResponse.response?.item)
                        getView()?.hideProgress()
                    }
            )
        }
    }
}