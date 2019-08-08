package com.oracle.common.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

object LoginRepository {

    operator fun invoke(context: Context): LoginRepository {
        return this
    }

    suspend fun doReachabilityTest(url: String): Boolean {
        var isReachable = false
        withContext(Dispatchers.IO) {
            try {
                val r = khttp.get(url = url, allowRedirects = true, timeout = 20.0)
                isReachable = r.statusCode in (200..300)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return isReachable
    }

    suspend fun basicAuth(username: String, password: String) {
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {

            }
        }
    }
}