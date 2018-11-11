package vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic

data class  Visitors(
        val views: Int,
        val visitors: Int,
        val mobile_views: Int,
        val sex: List<Sex>,
        val age: List<Age>,
        val sex_age: List<SexAge>,
        val countries: List<Country>,
        val cities: List<City>
)