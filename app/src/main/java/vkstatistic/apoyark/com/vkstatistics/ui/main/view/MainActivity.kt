package vkstatistic.apoyark.com.vkstatistics.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.ui.base.RxBus.RxBus
import vkstatistic.apoyark.com.vkstatistics.ui.base.RxBus.RxBusConst
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.BaseActivity
import vkstatistic.apoyark.com.vkstatistics.ui.main.interactor.MainMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.main.presenter.MainMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.ui.search.view.SearchResultFragment
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants
import vkstatistic.apoyark.com.vkstatistics.utils.extension.addFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE.toString())
    }

    override fun signedIn() {
        supportFragmentManager.addFragment(R.id.fragment_container, SearchResultFragment.newInstance(), SearchResultFragment.TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.onAttach(this)
        presenter.checkAuth()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken) {
                        presenter.checkAuth()
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
                RxBus.publish(RxBusConst.SEARCH_CODE, newText)
                return true
            }

        })

        return true
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
}
