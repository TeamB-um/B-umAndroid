package team.bum.ui.main.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import team.bum.databinding.FragmentDialogSheetBinding

class SheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDialogSheetBinding
    private lateinit var clickListener: ClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDialogSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mValues = arrayOf("즉시 삭제", "1일", "2일", "3일", "4일", "5일", "6일", "7일")
        setNumberPicker(binding.numberpicker, mValues)
        configureButton()
    }

    private fun setNumberPicker(numberPicker: NumberPicker, numbers: Array<String>) {
        numberPicker.maxValue = numbers.size - 1
        numberPicker.minValue = 0
        numberPicker.wrapSelectorWheel = false
        numberPicker.displayedValues = numbers
    }

    private fun configureButton() {
        binding.btnCheck.setOnClickListener {
            val date = getEditTextInNumberPicker()
            Log.d("야.", date)
            clickListener.onClickYes(date)
            dismiss()
        }
    }

    private fun getEditTextInNumberPicker(): String {
        when (binding.numberpicker.value) {
            0 -> {
                return "즉시 삭제"
            }
            1 -> {
                return "1일"
            }
            2 -> {
                return "2일"
            }
            3 -> {
                return "3일"
            }
            4 -> {
                return "4일"
            }
            5 -> {
                return "5일"
            }
            6 -> {
                return "6일"
            }
            else -> {
                return "7일"
            }
        }
    }

    fun setClickYesListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClickYes(date: String)
    }
}