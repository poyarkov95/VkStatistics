package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.ChartView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpFragment


class ChartFragment : BaseMvpFragment(), ChartView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun setUp() {
    }
}
