package com.oracle.dv

import android.preference.PreferenceManager
import androidx.lifecycle.MutableLiveData
import com.oracle.dv.datasets.OacDatabase
import com.oracle.dv.di.DaggerDVComponent
import com.oracle.dv.preferences.PreferencesDatabase
import com.oracle.dv.utils.AppPrefs
import com.oracle.regicidecommon.base.Sleeper
import com.oracle.regicidecommon.initializeCoreCommon
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class DVMobile : DaggerApplication() {

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreate() {
        super.onCreate()
        initializeCoreCommon(object: Sleeper {
            override fun sleepThread(delay: Long) {
                Thread.sleep(delay)
            }
        },
            AndroidSqliteDriver(OacDatabase.Schema, applicationContext, "oac.db"),
            AndroidSqliteDriver(PreferencesDatabase.Schema, applicationContext, "prefs.db"))
    }

    override fun applicationInjector(): AndroidInjector<DVMobile> {
        return DaggerDVComponent.builder()
            .prefs(PreferenceManager.getDefaultSharedPreferences(this))
            .build()
    }
}

fun <T> mutableLiveData(): MutableLiveData<T> = MutableLiveData()