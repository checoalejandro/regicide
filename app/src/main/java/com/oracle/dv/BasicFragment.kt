package com.oracle.dv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oracle.regicidecommon.base.BaseViewModel
import com.oracle.regicidecommon.base.Coordinator
import com.oracle.regicidecommon.base.State
import com.oracle.regicidecommon.base.StateChangeListener

abstract class BasicFragment<CD : Coordinator, ST : State, VM : BaseViewModel<CD, ST>> : Fragment(),
    StateChangeListener<ST> {

    protected lateinit var viewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onActive()
    }

    override fun onStop() {
        viewModel.onInactive()
        super.onStop()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}