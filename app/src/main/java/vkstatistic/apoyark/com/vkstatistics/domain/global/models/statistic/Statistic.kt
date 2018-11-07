package vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic

data class Statistic(
        val period_from: Int,
        val period_to: Int,
        val visitors: Visitors,
        val reach: Reach,
        val activity: List<Any>
)