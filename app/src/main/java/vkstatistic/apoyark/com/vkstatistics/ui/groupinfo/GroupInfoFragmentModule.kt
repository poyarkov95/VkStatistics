package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor.GroupInfoInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor.GroupInfoMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.presenter.GroupInfoMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.presenter.GroupInfoPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view.GroupInfoMVPView

@Module
class GroupInfoFragmentModule {

    @Provides
    internal fun provideGroupInfoInteractor(interactor: GroupInfoInteractor): GroupInfoMVPInteractor = interactor

    @Provides
    internal fun provideGroupInfoPresenter(presenter: GroupInfoPresenter<GroupInfoMVPView, GroupInfoMVPInteractor>)
            : GroupInfoMVPPresenter<GroupInfoMVPView, GroupInfoMVPInteractor> = presenter
}