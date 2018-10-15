package vkstatistic.apoyark.com.vkstatistics.ui.search.presenter

import vkstatistic.apoyark.com.vkstatistics.ui.base.presenter.MVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultMVPView

interface SearchResultMVPPresenter<V : SearchResultMVPView, I : SearchResultMVPInteractor> : MVPPresenter<V, I> {

    fun searchGroups(q: String)
}