package com.example.presentation.ui.fragment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.core.model.Course
import com.example.core.model.FilterArguments
import com.example.core.model.Sorting
import com.example.core_ui.base.BaseViewModel
import com.example.core_ui.utils.UIState
import com.example.domain.model.Tag
import com.example.domain.use_case.OnFavoritesClickUseCase
import com.example.domain.use_case.GetCoursesUseCase
import com.example.domain.use_case.GetTagsUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getTagsUseCase: GetTagsUseCase,
    private val addCourseToFavorites: OnFavoritesClickUseCase,
) : BaseViewModel() {

    private val _coursesState = mutableUiStateFlow<List<Course>>()
    val coursesState = _coursesState.asStateFlow()

    private val _tagsState = mutableUiStateFlow<List<Tag>>()
    val tagsState = _tagsState.asStateFlow()

    private var filterArguments: FilterArguments? = null

    init {
        getTags()
    }

    fun getCourses() {
        getCoursesUseCase(filterArguments?.tagId, filterArguments?.isPaid).gatherRequest(
            _coursesState
        )
    }

    private fun getTags() {
        getTagsUseCase().gatherRequest(_tagsState)
    }

    fun onFavoriteClick(item: Course) {
        viewModelScope.launch {
            addCourseToFavorites(item)
        }
    }

    fun setFilterArguments(filterArguments: FilterArguments?) {
        this.filterArguments = filterArguments
        getCourses()
    }

    /**
     * stepic api не предоставляет возможность сортировки
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun sortCoursesBy(sortType: Sorting) {
        _coursesState.update { state->
            if (state is UIState.Success) {
                UIState.Success(
                    when (sortType) {
                        Sorting.POPULARITY -> state.data.sortedByDescending {
                            it.learnersCount
                        }
                        Sorting.RATING -> state.data.sortedByDescending {
                            it.reviewSummary
                        }
                        Sorting.CREATION_DATE -> state.data.sortedByDescending {
                            Instant.parse(it.createDate).toEpochMilli()
                        }
                    }
                )
            } else {
                state
            }
        }
    }
}