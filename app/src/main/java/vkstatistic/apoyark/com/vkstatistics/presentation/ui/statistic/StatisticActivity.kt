package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic

import android.os.Bundle
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_statistic.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.di.statistic.DaggerStatisticComponent
import vkstatistic.apoyark.com.vkstatistics.di.statistic.StatisticComponent
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticPresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpActivity

class StatisticActivity : BaseMvpActivity(), StatisticView {

    @InjectPresenter
    internal lateinit var statisticPresenter: StatisticPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        initToolbar()

        statisticPresenter.findGroupStatistic(intent.extras.getInt(AppConstants.GROUP_ID_EXTRA))
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

        supportActionBar?.title = resources.getString(R.string.group_statistic)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun showStatistics(statisticModel: StatisticModel) {
        val statisticPagerAdapter = StatisticPagerAdapter(supportFragmentManager)
        statisticPagerAdapter.addFragment(PieChartFragment.newInstance(PieChartFragment.ChartType.CITIES, statisticModel), resources.getString(R.string.cities))
        statisticPagerAdapter.addFragment(PieChartFragment.newInstance(PieChartFragment.ChartType.AGE, statisticModel), resources.getString(R.string.age))
        statisticPagerAdapter.addFragment(PieChartFragment.newInstance(PieChartFragment.ChartType.GENDER, statisticModel), resources.getString(R.string.gender))
        view_pager.adapter = statisticPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun showViewContent() {

    }

    override fun hideViewContent() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showErrorView() {

    }

    override fun hideErrorView() {

    }

    override fun showErrorMessage(message: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        statisticPresenter.onDestroy()
    }
}
