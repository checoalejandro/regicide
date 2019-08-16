package com.oracle.dv.ui

import android.net.Uri
import android.os.Bundle
import com.oracle.dv.ui.dataset.DataSetFragment
import com.oracle.dv.R
import com.oracle.dv.utils.AppPrefs
import javax.inject.Inject

class MainActivity : BaseActivity(), DataSetFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}
