package com.example.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.core.model.Sorting
import com.example.presentation.databinding.BottomSheetSortingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingBottomSheet(
    private val sortingOptions: List<Sorting>,
    private val onSortingSelected: (Sorting) -> Unit
): BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetSortingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetSortingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sortingOptions.forEach { sorting ->
            val textView = TextView(requireContext()).apply {
                text = sorting.displayName
                textSize = 16f
                setPadding(16, 16, 16, 16)
                setOnClickListener {
                    onSortingSelected(sorting)
                    dismiss()
                }
            }
            binding.sortingOptionsContainer.addView(textView)
        }
    }
}