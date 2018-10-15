package vkstatistic.apoyark.com.vkstatistics.ui.search

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.presenter.SearchResultMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.presenter.SearchResultPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultAdapter
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultFragment
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultMVPView

@Module
class SearchFragmentModule {

    @Provides
    internal fun provideSearchResultInteractor(interactor: SearchResultInteractor): SearchResultMVPInteractor = interactor

    @Provides
    internal fun provideSearchResultFragment(presenter: SearchResultPresenter<SearchResultMVPView, SearchResultMVPInteractor>)
            : SearchResultMVPPresenter<SearchResultMVPView, SearchResultMVPInteractor> = presenter

    @Provides
    internal fun provideSearchResultProvider(): SearchResultAdapter = SearchResultAdapter(ArrayList())

    @Provides
    internal fun provideLayoutManager(fragment: SearchResultFragment) : LinearLayoutManager = LinearLayoutManager(fragment.activity)
}