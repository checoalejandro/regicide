package com.oracle.dv.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oracle.dv.BasicFragment
import com.oracle.dv.databinding.FragmentBasicLoginBinding
import com.oracle.regicidecommon.login.viewmodels.LoginCoordinator
import com.oracle.regicidecommon.login.viewmodels.LoginState
import com.oracle.regicidecommon.login.viewmodels.LoginViewModel
import com.oracle.regicidecommon.models.Host
import com.oracle.regicidecommon.reinitialize
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import java.util.*

class BasicLoginFragment : BasicFragment<LoginCoordinator, LoginState, LoginViewModel>(),
    LoginCoordinator {

    private lateinit var binding: FragmentBasicLoginBinding
    private val args: BasicLoginFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasicLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        return binding.root
    }

    @UseExperimental(InternalAPI::class)
    private fun doLogin() {
        val url = URLBuilder(args.url)
        val host = Host(url.host, url.port, url.protocol == URLProtocol.HTTPS)
        val user = binding.textUsername.text.toString()
        val password = binding.textPassword.text.toString()
        val encoded = "$user:$password".encodeBase64()
        reinitialize(host.toUrl(), encoded)

        viewModel.login(host, user, password, "en")
    }

    override fun onAttach(context: Context) {
        viewModel = LoginViewModel()
        viewModel.setCoordinator(this)
        viewModel.setStateChangeListener(this)
        viewModel.getDefaultUser()
        viewModel.getHosts()
        viewModel.getUsersByHost(args.url)
        super.onAttach(context)
    }

    override fun onStateChange(state: LoginState) {
        when (state.s) {
            "getUsersByHost" -> {
                state.usersByHost.forEach {
                    Log.d("Basic Login", "Users: ${it.host}")
                }
            }
            "getDefaultUser" -> {
                if (state.defaultUser == null) {
                    Log.d("Basic Login", "No default user")
                } else {
                    state.defaultUser?.let { user ->
                        Log.d("Basic Login", "Default user: ${user.username}")
                        binding.textUsername.setText(user.username)
                        binding.textPassword.setText(user.password)
                    }
                }
            }
            "getHosts" -> {
                state.hosts.forEach {
                    Log.d("Basic Login", "Host: ${it.toUrl()}")
                }
            }
            "login" -> {
                if (state.successful) {
                    showSearch()
                }
            }
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
