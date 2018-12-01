package vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupsearch

import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_group_search.*
import kotlinx.android.synthetic.main.item_group.view.*
import kotlinx.android.synthetic.main.network_error_view.view.*
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.di.groupsearch.DaggerGroupSearchComponent
import vkstatistic.apoyark.com.vkstatistics.di.groupsearch.GroupSearchComponent
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.Group
import vkstatistic.apoyark.com.vkstatistics.domain.global.models.group.GroupPrivacyConverter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.extension.loadImage
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main.GroupSearchPresenter
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.main.GroupSearchView
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.global.BaseMvpActivity
import vkstatistic.apoyark.com.vkstatistics.presentation.ui.groupinfo.GroupInfoActivity

class GroupSearchActivity : BaseMvpActivity(), GroupSearchView {

    @InjectPresenter
    internal lateinit var presenter: GroupSearchPresenter

    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun startSignIn() {
        VKSdk.login(this, AppConstants.DEFAULT_LOGIN_SCOPE.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_search)
        setSupportActionBar(toolbar)
        initRecyclerView()

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

                    override fun onError(error: VKError) {
                        showWarningDialog()
                    }
                })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showWarningDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(resources.getString(R.string.warning_dialog_title))
        dialogBuilder.setCancelable(false)
        dialogBuilder.setMessage(resources.getString(R.string.warning_dialog_message))
        dialogBuilder.setIcon(R.drawable.warning_icon)
        dialogBuilder.setPositiveButton(R.string.warning_dialog_button_text, { _, _ ->
            VKSdk.login(this, AppConstants.DEFAULT_LOGIN_SCOPE.toString())
        })
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.search_menu, menu)
        val menuItem: MenuItem? = menu?.findItem(R.id.action_search)
        search_view.setMenuItem(menuItem)

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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

    private fun initRecyclerView() {
        searchResultAdapter = SearchResultAdapter()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        search_recycler_view.layoutManager = layoutManager
        search_recycler_view.adapter = searchResultAdapter
    }

    override fun showSearchResult(searchResult: List<Group>?) {
        searchResultAdapter.addSearchResultToList(searchResult)
        search_recycler_view.visibility = View.VISIBLE
        no_network_view.visibility = View.GONE
        emptyRecyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        no_network_view.visibility = View.GONE
        search_recycler_view.visibility = View.GONE
        emptyRecyclerView.visibility = View.GONE
    }

    override fun showErrorView() {
        no_network_view.visibility = View.VISIBLE
        search_recycler_view.visibility = View.GONE
        emptyRecyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyRecyclerView() {
        emptyRecyclerView.visibility = View.VISIBLE
        search_recycler_view.visibility = View.GONE
        no_network_view.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    inner class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

        private val searchResult: ArrayList<Group> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchResultViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_group, parent, false))

        override fun getItemCount(): Int = searchResult.size

        override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.let {
            it.clear()
            it.onBind(position)
        }

        internal fun addSearchResultToList(searchResult: List<Group>?) {
            if (searchResult != null) {
                this.searchResult.clear()
                this.searchResult.addAll(searchResult)
                notifyDataSetChanged()
            }
        }

        inner class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun clear() {
                itemView.group_cricle_image.setImageBitmap(null)
                itemView.groupName_text_view.text = ""
                itemView.groupStatus_text_view.text = ""
            }

            fun onBind(position: Int) {
                val group = searchResult[position]
                inflateData(group.name, GroupPrivacyConverter(applicationContext).getByCode(group.is_closed), group.photo_100)
                setItemClickListener(group)
            }

            private fun setItemClickListener(group: Group) {
                itemView.setOnClickListener { view: View ->
                    val intent = Intent(view.context, GroupInfoActivity::class.java)
                    intent.putExtra(AppConstants.GROUP_ID_EXTRA, group.id)
                    intent.putExtra(AppConstants.GROUP_IMAGE_URL_EXTRA, group.getBigSizedGroupImageUrl())
                    intent.putExtra(AppConstants.GROUP_NAME_EXTRA, group.name)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this@GroupSearchActivity,
                                Pair<View, String>(view.group_cricle_image, this@GroupSearchActivity.getString(R.string.group_image_transition)))
                        view.context.startActivity(intent, activityOptions.toBundle())
                    } else {
                        view.context.startActivity(Intent(view.context, GroupInfoActivity::class.java))
                    }
                }
            }

            private fun inflateData(name: String?, isClosed: String?, imageUrl: String?) {
                name?.let { itemView.groupName_text_view.text = it }
                imageUrl?.let { itemView.group_cricle_image.loadImage(imageUrl) }
                isClosed?.let { itemView.groupStatus_text_view.text = isClosed }
            }
        }
    }
}
