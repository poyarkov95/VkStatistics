package vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupsearch

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_group_search.*
import kotlinx.android.synthetic.main.network_error_view.view.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.di.groupsearch.DaggerGroupSearchComponent
import vkstatistic.apoyark.com.vkstatistics.di.groupsearch.GroupSearchComponent
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main.GroupSearchPresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main.GroupSearchView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.adapters.SearchResultAdapter
import javax.inject.Inject

class GroupSearchActivity : BaseMvpActivity(), GroupSearchView {

    @InjectPresenter
    internal lateinit var presenter: GroupSearchPresenter

    @Inject
    internal lateinit var searchResultAdapter: SearchResultAdapter


    override fun startSignIn() {
        VKSdk.login(this, AppConstants.DEFAULT_LOGIN_SCOPE.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_search)
        setSupportActionBar(toolbar)
        setRecyclerView()

        no_network_view.retry_button.setOnClickListener({ presenter.retryLoad() })
    }

    @ProvidePresenter
    fun providePresenter(): GroupSearchPresenter {
        val component: GroupSearchComponent = DaggerGroupSearchComponent.builder()
                .appComponent(MyApplication.applicationComponent())
                .build()
        return component.getPresenter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken) {
                    }

                    override fun onError(error: VKError) {}
                })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.main_menu, menu)
        val menuItem: MenuItem? = menu?.findItem(R.id.action_search)
        search_view.setMenuItem(menuItem)

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    presenter.searchGroups(newText!!)
                }
                return true
            }

        })

        return true
    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        search_recycler_view.layoutManager = layoutManager
        search_recycler_view.adapter = searchResultAdapter
    }

    override fun showSearchResult(searchResult: List<Group>?) {
        searchResultAdapter.addSearchResultToList(searchResult)
        search_recycler_view.visibility = View.VISIBLE
    }

    override fun hideRecyclerView() {
        search_recycler_view.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorView() {
        no_network_view.visibility = View.VISIBLE
    }

    override fun hideErrorView() {
        no_network_view.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
