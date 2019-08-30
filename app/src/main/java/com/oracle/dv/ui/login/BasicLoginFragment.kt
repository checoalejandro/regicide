package com.oracle.dv.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oracle.dv.BasicFragment
import com.oracle.dv.databinding.FragmentBasicLoginBinding
import com.oracle.regicidecommon.login.viewmodels.LoginCoordinator
import com.oracle.regicidecommon.login.viewmodels.LoginState
import com.oracle.regicidecommon.login.viewmodels.LoginViewModel
import com.oracle.regicidecommon.reinitialize
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import java.util.*

class BasicLoginFragment : BasicFragment<LoginCoordinator, LoginState, LoginViewModel>(),
    LoginCoordinator {

    private lateinit var binding: FragmentBasicLoginBinding
    private val args: BasicLoginFragmentArgs by navArgs()

    @UseExperimental(InternalAPI::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasicLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            val user = binding.textUsername.text.toString()
            val password = binding.textPassword.text.toString()
            val encoded = "$user:$password".encodeBase64()
            reinitialize(args.url, encoded)
            viewModel.login(user, password, "en")
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        viewModel = LoginViewModel()
        viewModel.setCoordinator(this)
        viewModel.setStateChangeListener(this)
        super.onAttach(context)
    }

    override fun onStateChange(state: LoginState) {
        if (state.successful) {
            showSearch()
        }
    }

    override fun showSearch() {
        findNavController().navigate(
            BasicLoginFragmentDirections.actionBasicLoginFragmentToMainFragment(
                args.url,
                binding.textUsername.text.toString(),
                binding.textPassword.text.toString()
            )
        )
    }

}
