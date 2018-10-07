package vkstatistic.apoyark.com.vkstatistics.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import dagger.android.AndroidInjection
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.consts.ApiConstants
import vkstatistic.apoyark.com.vkstatistics.ui.login.interactor.LoginMVPInteractor
import vkstatistic.apoyark.com.vkstatistics.ui.login.presenter.LoginMVPPresenter
import vkstatistic.apoyark.com.vkstatistics.utils.CurrentUser
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginMVPView {

    @Inject
    internal lateinit var presenter: LoginMVPPresenter<LoginMVPView, LoginMVPInteractor>

    override fun startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE.toString())
    }

    override fun signedIn() {
        Toast.makeText(this, "Current user id: " + CurrentUser.getId(), Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.onAttach(this)
        presenter.checkAuth()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!// Пользователь успешно авторизовался
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken) {
                        presenter.checkAuth()
                    }
                    override fun onError(error: VKError) {}
                })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showProgress() {
        //not used
    }

    override fun hideProgress() {
        //not used
    }
}
