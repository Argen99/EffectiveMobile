package com.example.core_ui.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.core_ui.utils.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        launchObservers()
    }

    protected open fun initialize() {}
    protected open fun setupListeners() {}
    protected open fun launchObservers() {}

    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
        progressBar: View? = null
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                when (it) {
                    is UIState.Idle -> {
                        idle?.invoke(it)
                        progressBar?.isVisible = false
                    }
                    is UIState.Loading -> {
                        loading?.invoke(it)
                        progressBar?.isVisible = true
                    }

                    is UIState.Error -> {
                        error?.invoke(it.error)
                        progressBar?.isVisible = false
                    }

                    is UIState.Success -> {
                        success?.invoke(it.data)
                        progressBar?.isVisible = false
                    }
                }
            }
        }
    }

    private fun safeFlowGather(
        lifecycleState: Lifecycle.State,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }
}