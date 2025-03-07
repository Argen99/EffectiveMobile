package com.example.presentation.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core.model.Course
import com.example.core_ui.databinding.ItemCourseBinding
import com.example.core_ui.extensions.formatDate
import com.example.core_ui.extensions.setUrlImage
import com.example.presentation.R
import kotlin.random.Random

class FavoritesAdapter(
    private val onItemClick: (item: Course) -> Unit,
    private val onFavoriteClick: (item: Course) -> Unit,
): ListAdapter<Course, FavoritesAdapter.CourseViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CourseViewHolder(
        ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: Course): Unit = with(binding) {
            tvTitle.text = item.title
            tvSummary.text = item.summary
            tvRating.text = getRandomRating()
            tvDate.text = item.createDate.formatDate()
            ivBanner.setUrlImage(item.cover)
            tvPrice.apply {
                if (item.price == null) {
                    tvPrice.text = context.getString(com.example.core_ui.R.string.text_free)
                    setTextColor(ContextCompat.getColor(context, com.example.core_ui.R.color.green))
                } else {
                    tvPrice.text = item.displayPrice
                    setTextColor(ContextCompat.getColor(context, com.example.core_ui.R.color.white))
                }
            }
            setFavoriteIcon(item)
        }

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let(onItemClick)
            }
            binding.ivAddToFavorite.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    it.isFavorite = !it.isFavorite
                    setFavoriteIcon(it)
                    onFavoriteClick(it)
                }
            }
        }

        private fun setFavoriteIcon(item: Course) {
            if (item.isFavorite) {
                binding.ivAddToFavorite.setImageResource(com.example.core_ui.R.drawable.ic_favorite_active)
            } else {
                binding.ivAddToFavorite.setImageResource(com.example.core_ui.R.drawable.ic_favorite)
            }
        }

        private fun getRandomRating(): String {
            val randomValue = Random.nextDouble(5.0)
            return String.format("%.1f", randomValue)
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem == newItem
            }
        }
    }
}