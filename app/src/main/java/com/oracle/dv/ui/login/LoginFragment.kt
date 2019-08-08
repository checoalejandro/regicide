package com.oracle.dv.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.oracle.dv.R
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        setObservers()
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // TODO: Use the ViewModel
        button.setOnClickListener {
            val url = txtUrl.text.toString()
            if (url.isNotEmpty())
                viewModel.isReachable(txtUrl.text.toString())
        }
    }

    private fun setObservers() {
        viewModel.getIsReacheableLiveData().observe(this, Observer { result ->
            Toast.makeText(activity, "Url is reachable: $result", Toast.LENGTH_LONG).show()
            if (result) txtUrl.setText("")
        })
    }

}
