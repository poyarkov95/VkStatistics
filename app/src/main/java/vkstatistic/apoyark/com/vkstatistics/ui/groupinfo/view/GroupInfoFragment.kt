package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view


import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_group_info.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.BaseFragment
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.interactor.GroupInfoMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.presenter.GroupInfoMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants
import javax.inject.Inject

class GroupInfoFragment : BaseFragment(), GroupInfoMVPView {

    @Inject
    internal lateinit var presenter: GroupInfoMVPPresenter<GroupInfoMVPView, GroupInfoMVPInteractor>

    @Inject
    internal lateinit var googleFontTypeFace : Typeface

    companion object {
        internal const val TAG = "GroupInfoFragment"
        fun newInstance(): GroupInfoFragment = GroupInfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_info, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val groupId = arguments?.getInt(ApiConstants.GROUP_ID_EXTRA)
        presenter.searchGroup(groupId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUp() {
//        not used
    }

    override fun showGroup(group: Group?) {
        groupActivity_text_view.text = group?.activity
        creationDateIcon_text_view.typeface = googleFontTypeFace
        creationDate_text_view.text = formatDateString(group?.start_date)
        membersCount_icon_text_view.typeface = googleFontTypeFace
        membersCount_text_view.text = group?.members_count
        description_text_view.text = group?.description
    }

    private fun formatDateString(creationDate: String?): String {
        if (creationDate.isNullOrEmpty()) {
            return resources.getString(R.string.unknown_creation_date)
        }
        val delimiter = "/"
        val year = creationDate?.substring(IntRange(0, 3))
        val month = creationDate?.substring(IntRange(4, 5))
        val dey = creationDate?.substring(6, 7)
        return year.plus(delimiter).plus(month).plus(delimiter).plus(dey)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }

}
