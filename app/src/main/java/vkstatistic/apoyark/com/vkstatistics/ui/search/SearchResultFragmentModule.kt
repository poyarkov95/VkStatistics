package vkstatistic.apoyark.com.vkstatistics.ui.search

import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.presenter.SearchResultMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.presenter.SearchResultPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultAdapter
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultMVPView

@Module
class SearchResultFragmentModule {

    @Provides
    internal fun provideSearchResultInteractor(interactor: SearchResultInteractor): SearchResultMVPInteractor = interactor

    @Provides
    internal fun provideSearchResultPresenter(presenter: SearchResultPresenter<SearchResultMVPView, SearchResultMVPInteractor>)
            : SearchResultMVPPresenter<SearchResultMVPView, SearchResultMVPInteractor> = presenter

    @Provides
    internal fun provideSearchResultAdapter(): SearchResultAdapter = SearchResultAdapter(ArrayList())
}