package com.oracle.dv

import android.preference.PreferenceManager
import androidx.lifecycle.MutableLiveData
import com.oracle.dv.di.DaggerDVComponent
import com.oracle.dv.utils.AppPrefs
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class DVMobile : DaggerApplication() {

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<DVMobile> {
        return DaggerDVComponent.builder()
            .prefs(PreferenceManager.getDefaultSharedPreferences(this))
            .build()
    }
}

fun <T> mutableLiveData(): MutableLiveData<T> = MutableLiveData()