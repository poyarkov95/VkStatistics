package vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic

import vkstatistic.apoyark.com.vkstatistics.domain.global.models.ChartType
import java.io.Serializable

data class StatisticModel(val cityMap: Map<String, Float>,
                          val ageMap: Map<String, Float>,
                          val genderMap: Map<String, Float>) : Serializable {

    fun isAvailable(chartType: ChartType): Boolean {
        return when (chartType) {
            ChartType.CITIES -> cityMap.isNotEmpty()
            ChartType.AGE -> ageMap.isNotEmpty()
            ChartType.GENDER -> genderMap.isNotEmpty()
        }
    }
}