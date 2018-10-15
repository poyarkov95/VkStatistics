package vkstatistic.apoyark.com.vkstatistics.ui.search

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vkstatistic.apoyark.com.vkstatistics.di.module.NetworkModule
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultFragment

@Module
abstract class SearchResultFragmentProvider {

    @ContributesAndroidInjector(modules = [(SearchResultFragmentModule::class), (NetworkModule::class)])
    internal abstract fun proviceSearchResultFragmentModule(): SearchResultFragment
}