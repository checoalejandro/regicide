package com.oracle.dv.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.oracle.dv.BR
import com.oracle.dv.databinding.DatasetItemBinding
import com.oracle.dv.ui.main.DatasetViewModel
import io.greenerpastures.mvvm.list.SimpleViewModelListAdapter

class DatasetListAdapter: SimpleViewModelListAdapter<DatasetViewModel, ViewDataBinding>() {

    override val itemViewModelBindingVarId: Int = BR.viewModel

    override fun inflateView(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DatasetItemBinding.inflate(inflater, parent, false)
    }

    companion object {
        @JvmStatic
        fun create() = DatasetListAdapter()
    }
}