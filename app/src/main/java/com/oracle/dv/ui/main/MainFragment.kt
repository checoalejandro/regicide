package com.oracle.dv.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.oracle.dv.BR
import com.oracle.dv.R
import com.oracle.dv.databinding.MainFragmentBinding
import com.oracle.dv.ui.BaseFragment
import com.oracle.regicidecommon.oac.data.DatasetListState
import com.oracle.regicidecommon.oac.data.OACCoordinator
import com.oracle.regicidecommon.oac.data.OACListViewModel

class MainFragment :
    BaseFragment<OACCoordinator, DatasetListState, OACListViewModel, MainFragmentBinding>(),
    OACCoordinator {
    override fun showDataset(name: String) {
        findNavController().navigate(R.id.action_mainFragment_to_loginFragment2)
    }

    override fun onAttach(context: Context) {
        initialize(R.layout.main_fragment, BR.actions, BR.state, OACListViewModel())
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.view, this)
        viewModel.fetchDatasetList()
    }


    val onDatasetClicked = fun(name: String) {
        viewModel.onDatasetClicked(name)
    }
}
