package com.example.presentation.ui.fragment.course_info

import androidx.lifecycle.viewModelScope
import com.example.core.model.Course
import com.example.core_ui.base.BaseViewModel
import com.example.core_ui.utils.UIState
import com.example.domain.model.User
import com.example.domain.use_case.GetCourseByIdUseCase
import com.example.domain.use_case.OnFavoritesClickUseCase
import com.example.domain.use_case.GetUserUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseInfoViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val onFavoriteClickUseCase: OnFavoritesClickUseCase,
    private val getCourseByIdUseCase: GetCourseByIdUseCase
): BaseViewModel() {

    private val _courseState = mutableUiStateFlow<Course>()
    val courseState = _courseState.asStateFlow()

    private val _userState = mutableUiStateFlow<User>()
    val userState = _userState.asStateFlow()

    fun getUser(userId: Int) {
        getUserUseCase(userId).gatherRequest(_userState)
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            _courseState.update {
                if (it is UIState.Success) {
                    val res = UIState.Success(it.data.copy(isFavorite = !it.data.isFavorite))
                    onFavoriteClickUseCase(res.data)
                    res
                } else {
                    it
                }
            }
        }
    }

    fun getCourseById(id: Int) {
        getCourseByIdUseCase(id).gatherRequest(_courseState)
    }
}