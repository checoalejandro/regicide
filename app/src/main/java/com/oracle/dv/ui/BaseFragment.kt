package com.oracle.dv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.oracle.dv.BasicFragment
import com.oracle.regicidecommon.base.BaseViewModel
import com.oracle.regicidecommon.base.Coordinator
import com.oracle.regicidecommon.base.State
import com.oracle.regicidecommon.base.StateChangeListener

/**
 * Base [Fragment] used to map a [BaseViewModel] and handle its lifecycle events.
 */
open class BaseFragment<CD : Coordinator, ST : State, VM : BaseViewModel<CD, ST>, out B : ViewDataBinding> :
    BasicFragment<CD, ST, VM>(),
    StateChangeListener<ST> {

    private var isBindingInitialized: Boolean = false
    private var isInitialized: Boolean = false
    @LayoutRes
    private var bindingLayoutId: Int = 0
    private var actionsBindingVariableId: Int = 0
    private var stateBindingVariableId: Int = 0

    protected lateinit var binding: ViewDataBinding
    private var state: ST? = null

    /**
     * Initialize all DataBinding variables.
     */
    protected fun initialize(
        @LayoutRes bindingLayoutId: Int,
        @IdRes actionsBindingVariableId: Int,
        @IdRes stateBindingVariableId: Int,
        vm: VM
    ) {
        isInitialized = true
        this.bindingLayoutId = bindingLayoutId
        this.actionsBindingVariableId = actionsBindingVariableId
        this.stateBindingVariableId = stateBindingVariableId
        viewModel = vm
        viewModel.setStateChangeListener(this)
        this as? CD
            ?: throw UninitializedPropertyAccessException("Fragment does not implement the Coordinator interface!")
        viewModel.setCoordinator(this as CD)
    }

    override fun onStateChange(state: ST) {
        this.state = state
        if (isBindingInitialized)
            binding.setVariable(stateBindingVariableId, state)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        instanceState: Bundle?
    ): View? {
        if (!isInitialized)
            throw UninitializedPropertyAccessException("initialize() not called!")
        binding = DataBindingUtil.inflate<B>(inflater, bindingLayoutId, container, false)
        binding.setVariable(actionsBindingVariableId, viewModel)
        binding.setVariable(stateBindingVariableId, state)
        isBindingInitialized = true
        return binding.root
    }
}