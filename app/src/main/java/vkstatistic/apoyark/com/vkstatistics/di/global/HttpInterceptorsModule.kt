package vkstatistic.apoyark.com.vkstatistics.di.global

import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import vkstatistic.apoyark.com.vkstatistics.di.global.qualifiers.OkHttpLoggingInterceptor

@Module
class HttpInterceptorsModule {

    @Provides
    @OkHttpLoggingInterceptor
     fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}