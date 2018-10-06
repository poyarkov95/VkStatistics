package vkstatistic.apoyark.com.vkstatistics.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import vkstatistic.apoyark.com.vkstatistics.R
import vkstatistic.apoyark.com.vkstatistics.consts.ApiConstants




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE as String)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (!// Пользователь успешно авторизовался
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken) {}
                    override fun onError(error: VKError) {}
                })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
