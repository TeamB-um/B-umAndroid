package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import team.bum.R
import team.bum.databinding.FragmentFilterSheetBinding

class FilterSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFilterSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mValues = System.currentTimeMillis()
        setDatePicker(binding.dataPicker, mValues)
        binding.btnCheck.setOnClickListener {
            dismiss()
        }

        configureCategory()
    }

    private fun setDatePicker(datePicker: DatePicker, date: Long) {
        datePicker.maxDate = System.currentTimeMillis()
    }

    private fun configureCategory() {
        val category = listOf("인간관계", "인간관계", "인간관계", "인간관계", "인간관계", "인간관계", "인간관계")

        category.forEachIndexed { i, text ->
            if (i == 0) binding.chipGroup.addView(createChip(text, true))
            else binding.chipGroup.addView(createChip(text))
        }
    }

    private fun createChip(text: String, isChecked: Boolean = false): Chip {
        return (layoutInflater.inflate(R.layout.view_chip, binding.chipGroup, false) as Chip).apply {
            this.text = text
            this.isChecked = isChecked
            layoutParams =
                ChipGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}