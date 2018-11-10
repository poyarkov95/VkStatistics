package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.ProvidePresenter
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.di.statistic.StatisticComponent
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.ChartPresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.ChartView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpFragment


class ChartFragment : BaseMvpFragment(), ChartView {

    companion object {
        fun newInstance() = ChartFragment()
    }

    @ProvidePresenter
    fun providePresenter(): ChartPresenter {
        val component: StatisticComponent = DaggerStatisticComponent.builder()
                .appComponent(MyApplication.applicationComponent())
                .build()
        return component.getPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun setUp() {
    }
}
