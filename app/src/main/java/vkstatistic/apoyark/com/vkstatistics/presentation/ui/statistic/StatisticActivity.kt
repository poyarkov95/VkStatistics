package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_statistic.*
import kotlinx.android.synthetic.main.network_error_view.view.*
import kotlinx.android.synthetic.main.no_permissions_view.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.di.statistic.DaggerStatisticComponent
import vkstatistic.apoyark.com.vkstatistics.di.statistic.StatisticComponent
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.ChartType
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticPresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupsearch.GroupSearchActivity

class StatisticActivity : BaseMvpActivity(), StatisticView {

    @InjectPresenter
    internal lateinit var presenter: StatisticPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // would be better to get rid of it(

        initToolbar()

        no_network_view.retry_button.setOnClickListener({ presenter.retryLoad() })

        backToSearch_button.setOnClickListener({startActivity(Intent(this, GroupSearchActivity::class.java))})

        presenter.findGroupStatistic(intent.extras.getInt(AppConstants.GROUP_ID_EXTRA))
    }

    @ProvidePresenter
    fun providePresenter(): StatisticPresenter {
        val component: StatisticComponent = DaggerStatisticComponent.builder()
                .appComponent(MyApplication.applicationComponent())
                .build()
        return component.getPresenter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.statistic_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_research -> {
                startActivity(Intent(this, GroupSearchActivity::class.java))
            }
        }
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.title = "${intent.extras.getString(AppConstants.GROUP_NAME_EXTRA)} ${resources.getString(R.string.statistic)}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showStatistics(statisticModel: StatisticModel) {
        val statisticPagerAdapter = StatisticPagerAdapter(supportFragmentManager)
        statisticPagerAdapter.addFragment(PieChartFragment.newInstance(ChartType.CITIES, statisticModel, intent.extras.getString(AppConstants.GROUP_NAME_EXTRA)), resources.getString(R.string.cities))
        statisticPagerAdapter.addFragment(PieChartFragment.newInstance(ChartType.AGE, statisticModel, intent.extras.getString(AppConstants.GROUP_NAME_EXTRA)), resources.getString(R.string.age))
        statisticPagerAdapter.addFragment(PieChartFragment.newInstance(ChartType.GENDER, statisticModel, intent.extras.getString(AppConstants.GROUP_NAME_EXTRA)), resources.getString(R.string.gender))
        view_pager.adapter = statisticPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun showViewContent() {
        view_pager.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        no_network_view.visibility = View.GONE
        no_permissions_view.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        view_pager.visibility = View.GONE
        no_network_view.visibility = View.GONE
        no_permissions_view.visibility = View.GONE
    }

    override fun showNetworkErrorView() {
        no_network_view.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        view_pager.visibility = View.GONE
        no_permissions_view.visibility = View.GONE
    }

    override fun showNoPermissionsView() {
        no_permissions_view.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        view_pager.visibility = View.GONE
        no_network_view.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
