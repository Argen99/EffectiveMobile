package com.example.presentation.ui.fragment

import android.net.Uri
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.model.Course
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showToast
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFavoritesBinding
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {

    override val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModel by viewModel<FavoritesViewModel>()
    private val favoritesAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter(::onItemClick, ::onFavoriteClick)
    }

    override fun initialize() {
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }

    override fun launchObservers() {
        viewModel.favoritesState.spectateUiState(
            success = { favorites ->
                binding.tvPlaceHolder.isVisible = favorites.isEmpty()
                favoritesAdapter.submitList(favorites)
            },
            error = { message ->
                showToast(message)
            }
        )
    }

    private fun onItemClick(item: Course) {
        val deepLinkUri = Uri.parse("em://mainModule/courseInfoFragment/${item.id}")
        findNavController().navigate(deepLinkUri)
    }

    private fun onFavoriteClick(course: Course) {
        viewModel.onFavoriteClick(course)
    }
}