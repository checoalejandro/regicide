package com.oracle.dv.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oracle.regicidecommon.OACApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val oacApi = OACApi("http://slc11aso.us.oracle.com:9080/", "YWRtaW46d2VsY29tZTE=")

    fun test() {
        viewModelScope.launch {
            try {
                val dataset = oacApi.getDataset("admin", "Top Grossing Movies", success = {
                    println("Got ${it.name} dataset")
                }, failure = {
                    it?.printStackTrace()
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }

            try {
                val list = oacApi.getDatasets(success = {
                    println("Got ${it.count()} datasets")
                }, failure = {
                    it?.printStackTrace()
                })
            } catch (e: Exception) {

            }
        }
    }
}
