package vkstatistic.apoyark.com.vkstatistics.ui.search.view

import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface SearchResultMVPView : MVPView {

    fun showSearchResult(searchResult: List<Group>?)

    fun showProgress()

    fun hideProgress()
}