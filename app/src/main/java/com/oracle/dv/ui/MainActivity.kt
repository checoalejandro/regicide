package com.oracle.dv.ui

import android.os.Bundle
import com.oracle.dv.R
import com.oracle.dv.ui.login.LoginFragment
import com.oracle.dv.ui.main.MainFragment
import com.oracle.dv.utils.AppPrefs
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}
