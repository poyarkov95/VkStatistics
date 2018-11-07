package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic

import android.os.Bundle
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.StatisticView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpActivity

class StatisticActivity : BaseMvpActivity(), StatisticView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)
    }
}
