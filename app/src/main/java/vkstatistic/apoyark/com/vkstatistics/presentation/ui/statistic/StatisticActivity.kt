package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_statistic.*
import kotlinx.android.synthetic.main.network_error_view.view.*
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

class StatisticActivity : BaseMvpActivity(), StatisticView {

    @InjectPresenter
    internal lateinit var presenter: StatisticPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        initToolbar()

        no_network_view.retry_button.setOnClickListener({ presenter.retryLoad() })

        presenter.findGroupStatistic(intent.extras.getInt(AppConstants.GROUP_ID_EXTRA))
    }

    @ProvidePresenter
    fun providePresenter(): StatisticPresenter {
        val component: StatisticComponent = DaggerStatisticComponent.builder()
                .appComponent(MyApplication.applicationComponent())
                .build()
        return component.getPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
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
    }

    override fun hideViewContent() {
        view_pager.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorView() {
        no_network_view.visibility = View.VISIBLE
    }

    override fun hideErrorView() {
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
