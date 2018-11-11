package vkstatistic.apoyark.com.vkstatistics.presentation.ui.statistic


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_chart.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.statistic.ChartView

class PieChartFragment : Fragment(), ChartView {

    companion object {
        private val CHART_TYPE_TAG = "chart_tag"

        private lateinit var _statisticModel: StatisticModel

        fun newInstance(chartType: ChartType, statisticModel: StatisticModel): PieChartFragment {
            _statisticModel = statisticModel

            val fragment = PieChartFragment()
            val bundle = Bundle()
            bundle.putSerializable(CHART_TYPE_TAG, chartType)
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

     fun setUp() {
        pieChart.centerText = "Provide here group name"
        pieChart.setCenterTextSize(10f)

        pieChart.holeRadius = 45f
        pieChart.transparentCircleRadius = 50f

        pieChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL

        pieChart.legend.setDrawInside(false)

        val entries = ArrayList<PieEntry>()
        val chartType = arguments?.getSerializable(CHART_TYPE_TAG)

        when (chartType) {

            ChartType.CITIES -> {
                _statisticModel.cityMap.forEach { cityName, count -> entries.add(PieEntry(count, cityName)) }
            }

            ChartType.AGE -> {
                _statisticModel.ageMap.forEach { age, count -> entries.add(PieEntry(count, age)) }
            }

            ChartType.GENDER -> {
                _statisticModel.genderMap.forEach { gender, count -> entries.add(PieEntry(count, gender)) }
            }
        }

        val pieChartDataSet = PieDataSet(entries, "Put some text here")
        pieChartDataSet.colors = ColorTemplate.VORDIPLOM_COLORS.toList()
        pieChartDataSet.sliceSpace = 2f
        pieChartDataSet.valueLineColor = Color.WHITE
        pieChartDataSet.valueTextSize = 12f

        val pieData = PieData(pieChartDataSet)
        pieChart.data = pieData
    }

    enum class ChartType {
        CITIES, AGE, GENDER
    }
}
