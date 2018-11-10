package vkstatistic.apoyark.com.vkstatistics.domain.statistic

import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Inject

class StatisticInteractor @Inject constructor(private val groupRepository: GroupRepository, val schedulerProvider: SchedulerProvider) {

}