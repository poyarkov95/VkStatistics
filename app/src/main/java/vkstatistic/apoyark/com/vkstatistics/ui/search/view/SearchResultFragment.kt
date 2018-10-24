package vkstatistic.apoyark.com.vkstatistics.ui.search.view


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_search.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.ui.base.RxBus.RxBus
import vkstatistic.apoyark.com.vkstatistics.ui.base.RxBus.RxBusConst
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.BaseFragment
import vkstatistic.apoyark.com.vkstatistics.ui.search.interactor.SearchResultMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.search.presenter.SearchResultMVPPresenter
import javax.inject.Inject


class SearchResultFragment : BaseFragment(), SearchResultMVPView {
    companion object {
        internal val TAG = "SearchResultFragment"
        fun newInstance() : SearchResultFragment {
            return SearchResultFragment()
        }
    }

    @Inject
    internal lateinit var searchResultAdapter: SearchResultAdapter
    @Inject
    internal lateinit var presenter: SearchResultMVPPresenter<SearchResultMVPView, SearchResultMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_search, container, false)

    override fun setUp() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        search_recycler_view.layoutManager = layoutManager
        search_recycler_view.adapter = searchResultAdapter

        RxBus.subscribe(RxBusConst.SEARCH_CODE, this, Consumer { item ->
            if (item.toString().isNotEmpty()) {
                presenter.searchGroups(item.toString())
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_recycler_view.visibility = View.GONE
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showSearchResult(searchResult: List<Group>?) {
        searchResultAdapter.addSearchResultToList(searchResult)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        search_recycler_view.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        search_recycler_view.visibility = View.VISIBLE
    }


    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
        RxBus.unregister(this)
    }
}
