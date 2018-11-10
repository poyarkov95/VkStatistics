package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_statistic.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticView

class StatisticActivity : AppCompatActivity(), StatisticView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        initToolbar()
        initViews()
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

    private fun initViews() {
        setupViewPager(view_pager)
        tab_layout.setupWithViewPager(view_pager)
    }

    private fun setupViewPager(view_pager: ViewPager) {
        val statisticPagerAdapter = StatisticPagerAdapter(supportFragmentManager)
        statisticPagerAdapter.addFragment(ChartFragment.newInstance(), resources.getString(R.string.cities))
        statisticPagerAdapter.addFragment(ChartFragment.newInstance(), resources.getString(R.string.age))
        statisticPagerAdapter.addFragment(ChartFragment.newInstance(), resources.getString(R.string.gender))
        view_pager.adapter = statisticPagerAdapter
    }
}
