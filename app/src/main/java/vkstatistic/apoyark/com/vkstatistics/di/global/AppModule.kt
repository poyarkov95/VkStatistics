package vkstatistic.apoyark.com.vkstatistics.di.global

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.DisposableManager
import vkstatistic.apoyark.com.vkstatistics.presentation.mvp.global.SchedulerProvider
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideDisposableManageer(compositeDisposable: CompositeDisposable) = DisposableManager(compositeDisposable)

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    fun provideGoogleTypeface(context: Context): Typeface = Typeface.createFromAsset(context.assets, "MaterialIcons-Regular.ttf")
}