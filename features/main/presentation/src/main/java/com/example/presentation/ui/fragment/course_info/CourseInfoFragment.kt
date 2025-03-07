package com.example.presentation.ui.fragment.course_info

import android.util.Log
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.model.Course
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.asUri
import com.example.core_ui.extensions.formatDate
import com.example.core_ui.extensions.openInBrowser
import com.example.core_ui.extensions.setUrlImage
import com.example.core_ui.extensions.showToast
import com.example.domain.model.User
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCourseInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CourseInfoFragment :
    BaseFragment<FragmentCourseInfoBinding, CourseInfoViewModel>(R.layout.fragment_course_info) {

    override val binding by viewBinding(FragmentCourseInfoBinding::bind)
    override val viewModel by viewModel<CourseInfoViewModel>()
    private val navArgs by navArgs<CourseInfoFragmentArgs>()
    private var courseCanonicalUrl: String? = null

    override fun initialize() {
        activity?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
        }
        viewModel.getCourseById(navArgs.courseId)
    }

    override fun setupListeners(): Unit = with(binding) {
        btnGoToPlatform.setOnClickListener {
            courseCanonicalUrl?.asUri()?.openInBrowser(requireContext())
        }
        btnStartCourse.setOnClickListener {
            courseCanonicalUrl?.asUri()?.openInBrowser(requireContext())
        }
        ivFavorite.setOnClickListener {
            viewModel.onFavoriteClick()
        }
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun launchObservers() {
        viewModel.userState.spectateUiState(
            success = {
                setUserData(it)
            }
        )

        viewModel.courseState.spectateUiState(
            progressBar = binding.progressContainer,
            success = {
                courseCanonicalUrl = it.canonicalUrl
                setCourseData(it)
                setFavoriteIcon(it)
            },
            error = {
                showToast(it)
            }
        )
    }

    private fun setCourseData(item: Course): Unit = with(binding) {
        tvTitle.text = item.title
        ivCover.setUrlImage(item.cover)
        tvRating.text = getRandomRating()
        tvDate.text = item.createDate.formatDate()
        tvDescription.text = processHtmlToFormattedText(item.description)
        viewModel.getUser(item.owner)
    }

    private fun setFavoriteIcon(course: Course) {
        if (course.isFavorite) {
            binding.ivFavorite.setImageResource(com.example.core_ui.R.drawable.ic_favorite_active)
        } else {
            binding.ivFavorite.setImageResource(com.example.core_ui.R.drawable.ic_favorite)
        }
    }

    /**
     * Так как в модельке нет рейтинга, рейтинг генерируется рандомно
     */
    private fun getRandomRating(): String {
        val randomValue = Random.nextDouble(5.0)
        return String.format("%.1f", randomValue)
    }

    private fun processHtmlToFormattedText(html: String): String {
        val replacedHtml = html
            .replace("&nbsp;", " ")
            .replace("<br>", "\n")
            .replace("<[^>]*>".toRegex(), "")
        return replacedHtml.trim()
    }

    private fun setUserData(user: User): Unit = with(binding) {
        ivOwner.setUrlImage(user.avatar)
        tvOwner.text = user.fullName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, true)
        }
    }
}