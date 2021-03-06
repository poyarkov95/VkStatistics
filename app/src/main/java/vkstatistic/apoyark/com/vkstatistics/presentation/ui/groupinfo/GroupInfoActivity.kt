package vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupinfo

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_group_info.*
import kotlinx.android.synthetic.main.network_error_view.view.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.di.groupinfo.DaggerGroupInfoComponent
import vkstatistic.apoyark.com.vkstatistics.di.groupinfo.GroupInfoComponent
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.extension.loadImage
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo.GroupInfoPresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.groupinfo.GroupInfoView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic.StatisticActivity
import javax.inject.Inject

class GroupInfoActivity : BaseMvpActivity(), GroupInfoView {

    @InjectPresenter
    internal lateinit var presenter: GroupInfoPresenter

    @Inject
    internal lateinit var googleFontTypeFace: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        initToolbar()

        no_network_view.retry_button.setOnClickListener({ presenter.retryLoad() })

        viewStats_button.setOnClickListener({
            val newIntent = Intent(this, StatisticActivity::class.java)
            newIntent.putExtra(AppConstants.GROUP_ID_EXTRA, intent.extras.getInt(AppConstants.GROUP_ID_EXTRA))
            newIntent.putExtra(AppConstants.GROUP_NAME_EXTRA, intent.extras.getString(AppConstants.GROUP_NAME_EXTRA))
            startActivity(newIntent)
        })

        presenter.searchGroup(intent.extras.getInt(AppConstants.GROUP_ID_EXTRA))
    }

    @ProvidePresenter
    fun providePresenter(): GroupInfoPresenter {
        val component: GroupInfoComponent = DaggerGroupInfoComponent.builder()
                .appComponent(MyApplication.applicationComponent())
                .build()
        return component.getPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.title = intent.extras.getString(AppConstants.GROUP_NAME_EXTRA)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarImage.loadImage(intent.extras.getString(AppConstants.GROUP_IMAGE_URL_EXTRA))
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
        if (creationDate.isNullOrEmpty() || creationDate.equals("0")) {
            return resources.getString(R.string.unknown_creation_date)
        }
        val delimiter = "/"
        val year = creationDate?.substring(IntRange(0, 3))
        val month = creationDate?.substring(IntRange(4, 5))
        val dey = creationDate?.substring(6, 8)
        return year.plus(delimiter).plus(month).plus(delimiter).plus(dey)
    }


    override fun showViewContent() {
        groupInfoContent.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        no_network_view.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        groupInfoContent.visibility = View.GONE
        no_network_view.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorView() {
        no_network_view.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        groupInfoContent.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
