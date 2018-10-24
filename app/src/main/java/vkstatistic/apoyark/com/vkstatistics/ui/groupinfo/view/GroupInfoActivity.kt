package vkstatistic.apoyark.com.vkstatistics.ui.groupinfo.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_group_info.*
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.R.id.action_search
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.BaseActivity
import javax.inject.Inject

class GroupInfoActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            action_search -> {
                finish()
                return true
            }
        }
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>  = fragmentDispatchingAndroidInjector
}
