package com.example.presentation.ui.fragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.model.Course
import com.example.core.model.Sorting
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.safeNavigation
import com.example.core_ui.extensions.showToast
import com.example.domain.model.Tag
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.ui.adapter.CoursesAdapter
import com.example.presentation.ui.dialog.FilterBottomSheet
import com.example.presentation.ui.dialog.SortingBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel by viewModel<MainViewModel>()

    private val coursesAdapter: CoursesAdapter by lazy {
        CoursesAdapter(::onItemClick, ::onFavoriteClick)
    }
    private var tags: List<Tag> = emptyList()

    override fun initialize() {
        binding.rvCourse.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = coursesAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setupListeners(): Unit = with(binding) {
        tvSort.setOnClickListener {
            showSortBottomSheet()
        }
        ivFilter.setOnClickListener {
            showFilterBottomSheet()
        }
    }

    override fun launchObservers() {
        viewModel.coursesState.spectateUiState(
            progressBar = binding.progressBar,
            success = {
                coursesAdapter.submitList(it)
            },
            error = {
                showToast(it)
            }
        )

        viewModel.tagsState.spectateUiState(
            success = { tags = it },
            error = { showToast(it) }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showSortBottomSheet() {
        val sortingBottomSheet = SortingBottomSheet(
            sortingOptions = Sorting.entries,
            onSortingSelected = { selectedSorting ->
                binding.tvSort.text = selectedSorting.displayName
                viewModel.sortCoursesBy(selectedSorting)
            }
        )
        sortingBottomSheet.show(childFragmentManager, sortingBottomSheet.tag)
    }

    private fun showFilterBottomSheet() {
        val filterBottomSheet = FilterBottomSheet(
            tags = tags,
            onApplyClick = { filterArguments ->
                viewModel.setFilterArguments(filterArguments)
            }
        )
        filterBottomSheet.show(childFragmentManager, filterBottomSheet.tag)
    }

    private fun onItemClick(item: Course) {
        findNavController()
            .safeNavigation(MainFragmentDirections.actionMainFragmentToCourseInfoFragment(item.id))
    }

    private fun onFavoriteClick(item: Course) {
        viewModel.onFavoriteClick(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCourses()
    }
}