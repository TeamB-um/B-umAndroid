package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.view.get
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import team.bum.R
import team.bum.databinding.FragmentFilterSheetBinding
import team.bum.ui.main.archive.data.ArchiveWritingFilterInfo
import team.bum.util.*
import java.time.LocalDateTime

class FilterSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterSheetBinding
    private lateinit var clickListener: ClickListener
    private var isStart = true
    private var selectedId = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initFilter()
        configureButton()
        configureDatePicker()
        setDatePicker(binding.startPicker, binding.startDate)
        setSwitch()
        configureCategory()
    }

    private fun initFilter() {
        val dateString = LocalDateTime.now().dateString
        binding.startDate.text = dateString
        binding.endDate.text = dateString
    }

    private fun configureButton() {
        binding.btnCheck.setOnClickListener {
            val selectedChip = binding.chipGroup[getSelectedChip()] as Chip
            val filterInfo = ArchiveWritingFilterInfo(
                "${binding.startDate.text}", "${binding.endDate.text}", "${selectedChip.text}"
            )
            clickListener.onClickYes(filterInfo)
            dismiss()
        }
        binding.close.setOnClickListener { dismiss() }
    }

    private fun setDatePicker(datePicker: DatePicker, date: TextView) {
        datePicker.maxDate = System.currentTimeMillis()
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            val strDate = "$year.${(monthOfYear + 1).padZero()}.${dayOfMonth.padZero()}"
            date.text = strDate
        }
    }

    private fun configureDatePicker() {
        binding.startDate.setOnClickListener {
            binding.tvStart.setTextColor(getColor(R.color.filter_green))
            binding.startDivider.setBackgroundColor(getColor(R.color.filter_green))
            binding.tvEnd.setTextColor(getColor(R.color.icon_grey))
            binding.endDivider.setBackgroundColor(getColor(R.color.disable))
            binding.endPicker.setInvisible()
            binding.startPicker.setVisible()
            setDatePicker(binding.startPicker, binding.startDate)
            isStart = true
        }
        binding.endDate.setOnClickListener {
            binding.tvEnd.setTextColor(getColor(R.color.filter_green))
            binding.endDivider.setBackgroundColor(getColor(R.color.filter_green))
            binding.tvStart.setTextColor(getColor(R.color.icon_grey))
            binding.startDivider.setBackgroundColor(getColor(R.color.disable))
            binding.startPicker.setInvisible()
            binding.endPicker.setVisible()
            setDatePicker(binding.endPicker, binding.endDate)
            isStart = false
        }
    }

    private fun setSwitch() {
        binding.switchFilter.setOnCheckedChangeListener { _, onSwitch ->
            val switchViews = listOf(
                binding.tvStart, binding.tvEnd, binding.startDate, binding.endDate,
                binding.startDivider, binding.endDivider, binding.divider1, binding.divider2
            )
            if (onSwitch) {
                if (isStart) {
                    binding.startPicker.setVisible()
                    binding.endPicker.setInvisible()
                } else {
                    binding.startPicker.setInvisible()
                    binding.endPicker.setVisible()
                }
                initFilter()
                switchViews.forEach { it.setVisible() }
            } else {
                binding.startPicker.setGone()
                binding.endPicker.setGone()
                binding.startDate.text = ""
                binding.endDate.text = ""
                switchViews.forEach { it.setGone() }
            }
        }
    }

    private fun configureCategory() {
        val category = listOf("인간관계", "취업", "오늘하루", "우울", "건강", "웅앵웅")

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

    private fun getSelectedChip(): Int {
        for (i in 0 until binding.chipGroup.childCount) {
            val chip = binding.chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) selectedId = i
        }
        return selectedId
    }

    fun setClickYesListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClickYes(filterData: ArchiveWritingFilterInfo)
    }
}