package com.oracle.dv.ui.dataset

import com.oracle.regicidecommon.models.DataSet
import io.greenerpastures.mvvm.list.ItemViewModel

class DatasetViewModel (val dataset: DataSet, private val onClick: (namespace: String, name: String) -> Unit): ItemViewModel {
    override fun isSameContentAs(other: ItemViewModel): Boolean {
        return other is DatasetViewModel && other.dataset.name == dataset.name
    }

    override fun isSameItemAs(other: ItemViewModel): Boolean {
        return other is DatasetViewModel && other.dataset.name == dataset.name
    }

    fun onItemClicked() {
        onClick.invoke(dataset.namespace, dataset.name)
    }

}