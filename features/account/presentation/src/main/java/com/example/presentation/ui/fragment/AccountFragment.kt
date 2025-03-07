package com.example.presentation.ui.fragment

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAccountBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(R.layout.fragment_account) {
    override val binding by viewBinding(FragmentAccountBinding::bind)
    override val viewModel by viewModel<AccountViewModel>()
}