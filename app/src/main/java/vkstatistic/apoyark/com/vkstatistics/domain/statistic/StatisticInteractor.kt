package vkstatistic.apoyark.com.vkstatistics.domain.statistic

import io.reactivex.Single
import vkstatistic.apoyark.com.vkstatistics.domain.global.mappers.StatisticModelMapper
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.statistic.StatisticModel
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

class StatisticInteractor @Inject constructor(private val groupRepository: GroupRepository, val schedulerProvider: SchedulerProvider) {

    fun findGroupStatistic(groupId: String): Single<StatisticModel> {
        return groupRepository.findGroupStatistic(groupId)
                .flatMap { StatisticModelMapper.map(it) }
                .subscribeOn(schedulerProvider.io())
    }
}