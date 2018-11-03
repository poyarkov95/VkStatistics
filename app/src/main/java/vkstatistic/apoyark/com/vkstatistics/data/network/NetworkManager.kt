package vkstatistic.apoyark.com.vkstatistics.data.network

import android.content.Context
import android.net.ConnectivityManager
import vkstatistic.apoyark.com.vkstatistics.MyApplication

class NetworkManager {

    companion object {
        fun isConnected(): Boolean {
            val connectivityManager = MyApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting

        }
    }
}