package vkstatistic.apoyark.com.vkstatistics.di.module

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import vkstatistic.apoyark.com.vkstatistics.utils.SchedulerProvider
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun proviceCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    internal fun provideGoogleTypeface(context: Context) : Typeface = Typeface.createFromAsset(context.assets, "MaterialIcons-Regular.ttf")
}