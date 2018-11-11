package vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic

data class StatisticModel(val cityMap: Map<String, Float>,
                          val ageMap: Map<String, Float>,
                          val genderMap: Map<String, Float>)