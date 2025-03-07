package com.example.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.Tag
import com.example.presentation.databinding.ItemTagBinding

class TagAdapter : ListAdapter<Tag, TagAdapter.TagViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TagViewHolder(
        ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class TagViewHolder(private val binding: ItemTagBinding) : ViewHolder(binding.root) {

        fun onBind(item: Tag): Unit = with(binding) {
            rbTag.text = item.title
            rbTag.isChecked = item.isChecked
        }

        init {
            binding.rbTag.setOnClickListener {
                updateSelection(absoluteAdapterPosition)
            }
        }

        private fun updateSelection(newPosition: Int) {
            submitList(
                currentList.mapIndexed { index, tag ->
                    tag.copy(isChecked = index == newPosition)
                }
            )
        }
    }

    companion object : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }
    }
}