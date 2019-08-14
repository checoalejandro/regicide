package com.oracle.dv.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.oracle.dv.R
import com.oracle.dv.utils.HttpUtils
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setListeners()

        viewModel.test()
    }

    private fun setListeners() {
        btnConnect.setOnClickListener {
            context?.let {
                if (!HttpUtils.isConnected(it)) {
                    Toast.makeText(it, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                super.getView()?.findNavController()
                    ?.navigate(R.id.action_mainFragment_to_loginFragment2)
            }
        }
    }
}
