package vkstatistic.apoyark.com.vkstatistics.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vkstatistic.apoyark.com.vkstatistics.network.api.GroupApi
import vkstatistic.apoyark.com.vkstatistics.utils.ApiConstants

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiConstants.VK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGroupApi(retrofit: Retrofit) : GroupApi {
        return retrofit.create(GroupApi::class.java)
    }
}