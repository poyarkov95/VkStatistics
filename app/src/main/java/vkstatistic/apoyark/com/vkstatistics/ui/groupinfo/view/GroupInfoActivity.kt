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
import vkstatistic.apoyark.com.vkstatistics.R.id.action_back
import vkstatistic.apoyark.com.vkstatistics.network.model.Group
import vkstatistic.apoyark.com.vkstatistics.ui.base.view.BaseActivity
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants
import vkstatistic.apoyark.com.vkstatistics.utils.extension.addFragment
import vkstatistic.apoyark.com.vkstatistics.utils.extension.loadImage
import javax.inject.Inject

class GroupInfoActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        setSupportActionBar(toolbar)

        val group: Group = intent.extras[ApiConstants.GROUP_EXTRA] as Group

        supportActionBar?.title = group.name
        toolbarImage.loadImage(group.getBigSisedGroupImageUrl())

        initializeFragment(group.id)
    }

    private fun initializeFragment(groupId: Int) {
        val fragment = GroupInfoFragment.newInstance()
        val bundle = Bundle()
        bundle.putInt(ApiConstants.GROUP_ID_EXTRA, groupId)
        fragment.arguments = bundle
        supportFragmentManager.addFragment(R.id.fragment_container, fragment, GroupInfoFragment.TAG)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.group_info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            action_back -> {
                finish()
                return true
            }
        }
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>  = fragmentDispatchingAndroidInjector
}
