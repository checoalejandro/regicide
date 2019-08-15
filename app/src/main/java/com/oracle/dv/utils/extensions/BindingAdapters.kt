package com.oracle.dv.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.oracle.dv.ui.dataset.DatasetViewModel
import com.oracle.regicidecommon.models.DataSet
import io.greenerpastures.mvvm.list.ItemViewModel
import io.greenerpastures.mvvm.list.SimpleViewModelListAdapter

@BindingAdapter("textChanged")
fun textChangedListener(editText: EditText, textChanged: (text: String) -> Unit) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                textChanged(it.toString())
            }
        }
    })
}

//region RecyclerView
@BindingAdapter("hasFixedSize")
fun setHasFixedSize(recyclerView: RecyclerView, hasFixedSize: Boolean?) {
    hasFixedSize?.let {
        recyclerView.setHasFixedSize(it)
    }
}

@BindingAdapter("items")
fun setRecyclerViewItems(recyclerView: RecyclerView, items: List<ItemViewModel>?) {
    (recyclerView.adapter as SimpleViewModelListAdapter<ItemViewModel, ViewDataBinding>).submitList(
        items
    )
}

@BindingAdapter("items", "onItemClickListener")
fun setRecyclerViewDataset(
    recyclerView: RecyclerView,
    items: List<DataSet>?,
    onItemClickListener: (id: String) -> Unit
) {
    (recyclerView.adapter as SimpleViewModelListAdapter<ItemViewModel, ViewDataBinding>).submitList(
        items?.map {
            DatasetViewModel(it, onItemClickListener)
        })
}
//endregion