package team.bum.ui.main.setting

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import team.bum.databinding.FragmentDialogSheetBinding
import team.bum.util.dpToPixel

class SheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDialogSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDialogSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mValues = arrayOf("즉시", "1일", "3일", "5일", "7일")
        setNumberPicker(binding.numberpicker, mValues)
        binding.btnCheck.setOnClickListener {
            Log.d("test", getEditTextInNumberPicker())
        }
    }

    private fun setNumberPicker(numberPicker: NumberPicker, numbers: Array<String>) {
        numberPicker.maxValue = numbers.size - 1
        numberPicker.minValue = 0
        numberPicker.wrapSelectorWheel = true
        numberPicker.displayedValues = numbers
    }

    private fun getEditTextInNumberPicker(): String {
        when (binding.numberpicker.value) {
            0 -> {
                return "즉시"
            }
            1 -> {
                return "1일"
            }
            2 -> {
                return "3일"
            }
            3 -> {
                return "5일"
            }
            else -> {
                return "7일"
            }
        }
    }
}
