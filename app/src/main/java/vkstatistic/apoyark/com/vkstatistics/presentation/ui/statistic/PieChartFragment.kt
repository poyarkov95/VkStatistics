package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic


import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_chart.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants.CHART_MODEL
import vkstatistic.apoyark.com.vkstatistics.AppConstants.CHART_TYPE_TAG
import vkstatistic.apoyark.com.vkstatistics.AppConstants.GROUP_NAME_EXTRA
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.ChartType
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.ChartView

class PieChartFragment : Fragment(), ChartView, SeekBar.OnSeekBarChangeListener {

    companion object {

        fun newInstance(chartType: ChartType, statisticModel: StatisticModel, groupName: String): PieChartFragment {
            val fragment = PieChartFragment()
            val bundle = Bundle()
            bundle.putSerializable(CHART_TYPE_TAG, chartType)
            bundle.putSerializable(CHART_MODEL, statisticModel)
            bundle.putString(GROUP_NAME_EXTRA, groupName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        pieChart.invalidate()
    }

    private fun setUp() {
        val statisticModel = arguments?.getSerializable(CHART_MODEL) as StatisticModel
        val chartType = arguments?.getSerializable(CHART_TYPE_TAG) as ChartType

        if (statisticModel.isAvailable(chartType)) {
            emptyChartView.visibility = View.GONE
            chartContent.visibility = View.VISIBLE

            drawChart(statisticModel, chartType)
        } else {
            emptyChartView.visibility = View.VISIBLE
            chartContent.visibility = View.GONE
        }
    }

    private fun drawChart(statisticModel: StatisticModel, chartType: ChartType) {
        val groupName = arguments?.getString(GROUP_NAME_EXTRA)
        pieChart.centerText = "$groupName ${resources.getString(R.string.chart_description)}"

        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.setExtraOffsets(10f, 10f, 10f, 30f)
        pieChart.setCenterTextSize(10f)
        pieChart.holeRadius = 60f
        pieChart.transparentCircleRadius = 50f

        pieChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.xOffset = 10f
        pieChart.legend.yOffset = 10f


        pieChart.description.isEnabled = false
        pieChart.legend.setDrawInside(false)
        pieChart.setEntryLabelColor(Color.BLACK)

        seekBar.setOnSeekBarChangeListener(this)
        val chartSize = statisticModel.getSize(chartType)
        seekBar.max = chartSize
        val chartProgress = calculateProgress(chartSize)
        seekBar.progress = chartProgress

        setData(chartProgress)
    }

    private fun setData(size: Int) {
        val statisticModel = arguments?.getSerializable(CHART_MODEL) as StatisticModel
        val chartType = arguments?.getSerializable(CHART_TYPE_TAG) as ChartType
        val entries = ArrayList<PieEntry>()

        when (chartType) {

            ChartType.CITIES -> {
                statisticModel.cityStat.take(size).forEach { pair -> entries.add(PieEntry(pair.second, pair.first)) }
            }

            ChartType.AGE -> {
                statisticModel.ageStat.take(size).forEach { pair -> entries.add(PieEntry(pair.second, "${pair.first} ${resources.getString(R.string.years)}")) }
            }

            ChartType.GENDER -> {
                statisticModel.genderStat.take(size).forEach { pair -> entries.add(PieEntry(pair.second, pair.first)) }
            }
        }

        val pieChartDataSet = PieDataSet(entries, arguments?.getString(GROUP_NAME_EXTRA))

        pieChartDataSet.colors = getChartColors()
        pieChartDataSet.sliceSpace = 2f
        pieChartDataSet.valueLineColor = Color.BLACK
        pieChartDataSet.valueTextColor = Color.BLACK
        pieChartDataSet.valueTextSize = 12f

        val pieData = PieData(pieChartDataSet)
        pieChart.data = pieData
        pieChart.invalidate()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        seekBarProgress.text = "${resources.getString(R.string.amount_of_presenting_date)} $progress"

        setData(progress)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        //not used
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        //not used
    }

    private fun calculateProgress(dataSize: Int): Int {
        when (dataSize) {
            1 -> return 1
            2 -> return 2
        }
        return dataSize.div(2)
    }

    private fun getChartColors() : List<Int> {
        val colors = ArrayList<Int>()

        ColorTemplate.VORDIPLOM_COLORS.toList().forEach { colors.add(it) }
        ColorTemplate.JOYFUL_COLORS.toList().forEach { colors.add(it) }
        ColorTemplate.COLORFUL_COLORS.toList().forEach { colors.add(it) }
        ColorTemplate.LIBERTY_COLORS.toList().forEach { colors.add(it) }
        ColorTemplate.PASTEL_COLORS.toList().forEach { colors.add(it) }

        return colors
    }
}
