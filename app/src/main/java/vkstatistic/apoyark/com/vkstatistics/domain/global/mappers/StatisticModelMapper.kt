package vkstatistic.apoyark.com.vkstatistics.domain.global.mappers

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.Statistic
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel

class StatisticModelMapper {

    companion object {
        fun map(statistic: List<Statistic>): Single<StatisticModel> { //todo think how to do it in a clear way
            val cityMap = HashMap<String, Float>()
            val ageMap = HashMap<String, Float>()
            val genderMap = HashMap<String, Float>()

            statistic.forEach {
                if(it.visitors.cities != null && it.visitors.cities.isNotEmpty()) {
                    it.visitors.cities.forEach {
//                        if (cityMap[it.name] != null) {
//                            cityMap[it.name] = cityMap[it.name]!!.plus(it.count.toFloat())
//                        } else {
                            cityMap[it.name] = it.count.toFloat()
//                        }
                    }
                }

            if(it.visitors.age != null && it.visitors.age.isNotEmpty()) {
                it.visitors.age.forEach {
//                    if (cityMap[it.value] != null) {
//                        cityMap[it.value] = cityMap[it.value]!!.plus(it.count.toFloat())
//                    } else {
                        ageMap[it.value] = it.count.toFloat()
//                    }
                }

            }

                if(it.visitors.sex != null && it.visitors.sex.isNotEmpty()) {
                    it.visitors.sex.forEach {
//                        if (cityMap[it.value] != null) {
//                            cityMap[it.value] = cityMap[it.value]!!.plus(it.count.toFloat())
//                        } else {
                            genderMap[it.value] = it.count.toFloat()
                        }
//                    }
                }

            }



            return Single.just(StatisticModel(cityMap, ageMap, genderMap))
        }
    }
}