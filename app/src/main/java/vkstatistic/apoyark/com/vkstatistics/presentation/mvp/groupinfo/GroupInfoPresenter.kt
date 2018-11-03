package vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo

import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.domain.groupinfo.GroupInfoInteractor
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.BasePresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import java.util.logging.Logger
import javax.inject.Inject

@InjectViewState
class GroupInfoPresenter @Inject constructor(private val groupInfoInteractor: GroupInfoInteractor,
                                             private val compositeDisposable: CompositeDisposable,
                                             private val schedulerProvider: SchedulerProvider) :
        BasePresenter<GroupInfoView>(compositeDisposable) {

    fun searchGroup(groupId: Int) {
        viewState.showProgress()

        compositeDisposable.add(
                groupInfoInteractor.findGroupById(groupId.toString())
                        .observeOn(schedulerProvider.mainThread())
                        .subscribe ( {group ->
                            viewState.hideProgress()
                            viewState.showGroup(group)},
                                {
                                    Logger.getLogger(GroupInfoPresenter::class.simpleName, it.message)
                                }
                            )
        )
    }
}