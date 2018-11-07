package vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic

data class Reach(
        val reach: Int,
        val reach_subscribers: Int,
        val mobile_reach: Int,
        val sex: List<Sex>,
        val age: List<Age>,
        val sex_age: List<SexAge>,
        val countries: List<Country>,
        val cities: List<Any>
)