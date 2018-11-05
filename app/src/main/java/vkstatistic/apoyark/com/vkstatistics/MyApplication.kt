package vkstatistic.apoyark.com.vkstatistics

import android.app.Activity
import android.app.Application
import android.content.Context
import com.vk.sdk.VKSdk
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import okhttp3.internal.Internal.instance
import vkstatistic.apoyark.com.vkstatistics.di.component.AppComponent
import vkstatistic.apoyark.com.vkstatistics.di.component.DaggerAppComponent
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        applicationComponent?.inject(this)

        VKSdk.initialize(this)
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
        private var applicationComponent: AppComponent? = null

        fun applicationComponent () = applicationComponent
    }
}