package com.example.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.model.FilterArguments
import com.example.core.model.Sorting
import com.example.domain.model.Tag
import com.example.presentation.databinding.BottomSheetFilterBinding
import com.example.presentation.ui.adapter.TagAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheet(
    private val tags: List<Tag?>?,
    private val onApplyClick: (FilterArguments?) -> Unit,
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFilterBinding
    private val tagsAdapter: TagAdapter by lazy {
        TagAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.post {
            val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        binding.rvFilterItems.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = tagsAdapter
        }
        tagsAdapter.submitList(tags)

        binding.btnApply.setOnClickListener {
            val tag = tagsAdapter.currentList.find { it.isChecked }?.id
            val isPaid = !binding.switchFree.isChecked
            onApplyClick(FilterArguments(isPaid ,tag))
            dismiss()
        }

        binding.btnReset.setOnClickListener {
            onApplyClick(null)
            dismiss()
        }
    }
}