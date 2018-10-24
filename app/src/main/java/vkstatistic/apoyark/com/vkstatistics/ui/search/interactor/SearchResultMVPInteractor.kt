package vkstatistic.apoyark.com.vkstatistics.ui.search.interactor

import io.reactivex.Observable
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.network.response.searchgroup.SearchGroupResponse
import vkstatistic.apoyark.com.vkstatistics.ui.base.interactor.MVPInteractor

interface SearchResultMVPInteractor : MVPInteractor {

    fun getGroupList(q: String) : Observable<SearchGroupResponse<Group>>
}