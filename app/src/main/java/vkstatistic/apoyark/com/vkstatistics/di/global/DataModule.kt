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
import vkstatistic.apoyark.com.vkstatistics.data.network.NetworkChecker
import vkstatistic.apoyark.com.vkstatistics.data.network.interseptors.NetworkCheckerInterceptor
import vkstatistic.apoyark.com.vkstatistics.data.repositories.GroupRepositoryImpl
import vkstatistic.apoyark.com.vkstatistics.di.global.qualifiers.OkHttpLoggingInterceptor
import vkstatistic.apoyark.com.vkstatistics.domain.global.repositories.GroupRepository
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Provides
    fun provideGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository = groupRepositoryImpl

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConstants.VK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideClient(networkChecker: NetworkChecker, @OkHttpLoggingInterceptor httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val networkCheckerInterceptor = NetworkCheckerInterceptor(networkChecker)
        return OkHttpClient.Builder()
                .addInterceptor(networkCheckerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    fun provideNetworkChecker() = NetworkChecker()


    @Provides
    fun provideGroupApi(retrofit: Retrofit): GroupApi {
        return retrofit.create(GroupApi::class.java)
    }
}