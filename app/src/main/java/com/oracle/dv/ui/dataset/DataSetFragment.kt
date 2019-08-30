package com.oracle.dv.ui.dataset

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.oracle.dv.BR
import com.oracle.dv.R
import com.oracle.dv.databinding.DatasetFragmentBinding
import com.oracle.dv.databinding.DatasetItemBinding
import com.oracle.dv.ui.BaseFragment
import com.oracle.regicidecommon.base.Coordinator
import com.oracle.regicidecommon.oac.viewmodels.DataSetDetailState
import com.oracle.regicidecommon.oac.viewmodels.DataSetDetailViewModel

class DataSetFragment :
    BaseFragment<Coordinator, DataSetDetailState, DataSetDetailViewModel, DatasetItemBinding>(),
    Coordinator {
    private val args: DataSetFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.view, this)
        binding.let {
            if (it is DatasetFragmentBinding) {
                it.btnGetData.setOnClickListener {
                    viewModel.fetchCanonicalData(args.namespace, args.name)
                }
            }
        }
    }


    override fun onAttach(context: Context) {
        initialize(
            R.layout.dataset_fragment,
            BR.actions,
            BR.state,
            DataSetDetailViewModel(args.namespace, args.name)
        )
        super.onAttach(context)
    }

}
