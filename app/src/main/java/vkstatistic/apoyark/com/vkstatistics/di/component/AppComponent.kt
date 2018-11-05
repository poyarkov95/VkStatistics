package vkstatistic.apoyark.com.vkstatistics.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import vkstatistic.apoyark.com.vkstatistics.MyApplication
import vkstatistic.apoyark.com.vkstatistics.di.builder.ActivityBuilder
import vkstatistic.apoyark.com.vkstatistics.di.global.AppModule
import vkstatistic.apoyark.com.vkstatistics.di.global.DataModule
import vkstatistic.apoyark.com.vkstatistics.di.global.HttpInterceptorsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (DataModule::class), (ActivityBuilder::class), (HttpInterceptorsModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}