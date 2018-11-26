package vkstatistic.apoyark.com.vkstatistics.domain.global.mappers

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.Statistic
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel

class StatisticModelMapper {

    companion object {
        fun map(statistic: List<Statistic>): Single<StatisticModel> {
            val cityMap = LinkedHashMap<String, Float>()
            val ageMap = LinkedHashMap<String, Float>()
            val genderMap = LinkedHashMap<String, Float>()

            statistic.forEach {
                it.visitors.cities?.forEach {
                    if (cityMap.containsKey(it.name)) {
                        cityMap[it.name] = cityMap[it.name]!! + it.count
                    } else {
                        cityMap[it.name] = it.count.toFloat()
                    }
                }

                it.visitors.age?.forEach {
                    if (ageMap.containsKey(it.value)) {
                        ageMap[it.value] = ageMap[it.value]!! + it.count
                    } else {
                        ageMap[it.value] = it.count.toFloat()
                    }
                }

                it.visitors.sex?.forEach {
                    if (genderMap.containsKey(it.value)) {
                        genderMap[it.value] = genderMap[it.value]!! + it.count
                    } else {
                        genderMap[it.value] = it.count.toFloat()
                    }
                }
            }
            return Single.just(StatisticModel(cityMap.toList(), ageMap.toList(), genderMap.toList()))
        }
    }
}