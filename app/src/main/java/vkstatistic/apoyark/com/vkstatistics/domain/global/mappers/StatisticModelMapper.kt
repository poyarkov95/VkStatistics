package vkstatistic.apoyark.com.vkstatistics.domain.global.mappers

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.Statistic
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel

class StatisticModelMapper {

    companion object {
        fun map(statistic: List<Statistic>): Single<StatisticModel> {
            val cityMap = HashMap<String, Float>()
            val ageMap = HashMap<String, Float>()
            val genderMap = HashMap<String, Float>()

            statistic.forEach {
                it.visitors.cities?.forEach {
                    if (cityMap.containsKey(it.name)) {
                        cityMap[it.name]?.plus(it.count)
                    } else {
                        cityMap[it.name] = it.count.toFloat()
                    }
                }


                it.visitors.age?.forEach {
                    if (cityMap.containsKey(it.value)) {
                        cityMap[it.value]?.plus(it.count)
                    } else {
                        ageMap[it.value] = it.count.toFloat()
                    }
                }

                it.visitors.sex?.forEach {
                    if (cityMap.containsKey(it.value)) {
                        cityMap[it.value]?.plus(it.count)
                    } else {
                        genderMap[it.value] = it.count.toFloat()
                    }
                }
            }

            return Single.just(StatisticModel(cityMap, ageMap, genderMap))
        }
    }
}