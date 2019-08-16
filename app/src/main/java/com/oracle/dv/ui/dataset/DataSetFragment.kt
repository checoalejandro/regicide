package com.oracle.dv.ui.dataset

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.oracle.dv.BR
import com.oracle.dv.R
import com.oracle.dv.databinding.DatasetItemBinding
import com.oracle.dv.ui.BaseFragment
import com.oracle.regicidecommon.base.Coordinator
import com.oracle.regicidecommon.oac.viewmodels.DataSetDetailState
import com.oracle.regicidecommon.oac.viewmodels.DataSetDetailViewModel

class DataSetFragment :
    BaseFragment<Coordinator, DataSetDetailState, DataSetDetailViewModel, DatasetItemBinding>(),
    Coordinator {
    private var listener: OnFragmentInteractionListener? = null

    private val args: DataSetFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.view, this)
    }


    override fun onAttach(context: Context) {
        initialize(
            R.layout.dataset_fragment,
            BR.actions,
            BR.state,
            DataSetDetailViewModel(args.namespace, args.name)
        )
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}