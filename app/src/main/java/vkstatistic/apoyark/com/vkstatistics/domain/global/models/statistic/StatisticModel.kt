package vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic

import vkstatistic.apoyark.com.vkstatistics.domain.global.models.ChartType
import java.io.Serializable

data class StatisticModel(val cityStat: List<Pair<String, Float>>,
                          val ageStat: List<Pair<String, Float>>,
                          val genderStat: List<Pair<String, Float>>): Serializable {

    fun isAvailable(chartType: ChartType): Boolean {
        return when (chartType) {
            ChartType.CITIES -> cityStat.isNotEmpty()
            ChartType.AGE -> ageStat.isNotEmpty()
            ChartType.GENDER -> genderStat.isNotEmpty()
        }
    }

    fun getSize(chartType: ChartType): Int {
        return when (chartType) {
            ChartType.CITIES -> cityStat.size
            ChartType.AGE -> ageStat.size
            ChartType.GENDER -> genderStat.size
        }
    }
}