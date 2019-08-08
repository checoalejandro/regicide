package com.oracle.dv.di

import android.content.SharedPreferences
import com.oracle.dv.DVMobile
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class])
interface DVComponent : AndroidInjector<DVMobile> {

    @Component.Builder
    interface Builder {
        fun build(): DVComponent

        @BindsInstance
        fun prefs(prefs: SharedPreferences): Builder
    }
}