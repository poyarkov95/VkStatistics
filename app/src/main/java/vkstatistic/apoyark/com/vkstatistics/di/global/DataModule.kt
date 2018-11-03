package vkstatistic.apoyark.com.vkstatistics.di.global

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vkstatistic.apoyark.com.vkstatistics.AppConstants
import vkstatistic.apoyark.com.vkstatistics.data.network.GroupApi
import vkstatistic.apoyark.com.vkstatistics.data.repositories.GroupRepositoryImpl
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Provides
    internal fun provideGroupRepository(groupRepositoryImpl: GroupRepositoryImpl) : GroupRepository = groupRepositoryImpl

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConstants.VK_BASE_URL)
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