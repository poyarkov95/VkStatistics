package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val statisticModel = arguments?.getSerializable(CHART_MODEL) as StatisticModel
        val chartType = arguments?.getSerializable(CHART_TYPE_TAG) as ChartType

        if (statisticModel.isAvailable(chartType)) {
            emptyChartView.visibility = View.GONE
            chartContentFrame.visibility = View.VISIBLE

            drawChart(statisticModel, chartType)
        } else {
            emptyChartView.visibility = View.VISIBLE
            chartContentFrame.visibility = View.GONE
        }
    }

    private fun drawChart(statisticModel: StatisticModel, chartType: ChartType) {
        val groupName = arguments?.getString(GROUP_NAME_EXTRA)
        pieChart.centerText = "'$groupName' ${resources.getString(R.string.chart_description)}"

        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.extraBottomOffset = 90f
        pieChart.setCenterTextSize(10f)
        pieChart.holeRadius = 60f
        pieChart.transparentCircleRadius = 50f

        pieChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL


        pieChart.description.isEnabled = false
        pieChart.legend.setDrawInside(false)

        val entries = ArrayList<PieEntry>()

        when (chartType) {

            ChartType.CITIES -> {
                statisticModel.cityMap.forEach { cityName, count -> entries.add(PieEntry(count, cityName)) }
            }

            ChartType.AGE -> {
                statisticModel.ageMap.forEach { age, count -> entries.add(PieEntry(count, age)) }
            }

            ChartType.GENDER -> {
                statisticModel.genderMap.forEach { gender, count -> entries.add(PieEntry(count, gender)) }
            }
        }

        val pieChartDataSet = PieDataSet(entries, groupName)
        pieChartDataSet.colors = ColorTemplate.VORDIPLOM_COLORS.toList()
        pieChartDataSet.sliceSpace = 2f
        pieChartDataSet.valueLineColor = Color.WHITE
        pieChartDataSet.valueTextSize = 12f

        val pieData = PieData(pieChartDataSet)
        pieChart.data = pieData
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}
