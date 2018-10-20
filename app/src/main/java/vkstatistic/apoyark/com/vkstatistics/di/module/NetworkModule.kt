package vkstatistic.apoyark.com.vkstatistics.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vkstatistic.apoyark.com.vkstatistics.network.api.GroupApi
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants
import javax.inject.Singleton

@Module
@Singleton
class NetworkModule {

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiConstants.VK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    internal fun provideClient() : OkHttpClient {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }


    @Provides
    internal fun provideGroupApi(retrofit: Retrofit) : GroupApi {
        return retrofit.create(GroupApi::class.java)
    }
}