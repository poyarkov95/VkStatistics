package vkstatistic.apoyark.com.vkstatistics.ui.search.view

import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.MVPView

interface SearchMVPView : MVPView {

    fun showSearchResult(searchResult: MutableList<Group>)
}