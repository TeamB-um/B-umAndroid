package team.bum.ui.main.home.drop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import team.bum.R
import team.bum.databinding.FragmentHomeDropBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.util.getColor

class HomeDropFragment : BaseFragment<FragmentHomeDropBinding>() {

    private val isDelete
        get() = arguments?.getBoolean("isDelete") ?: false

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeDropBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDrop()
        configureDropNavigation()
    }

    private fun initDrop() {
        if (isDelete) {
            binding.headerTitle.text = "삭제 휴지통"
            binding.toastText.text = buildSpannedString {
                append("작성된 글은 ")
                color(getColor(R.color.blue_3)) {
                    append("삭제 휴지통")
                }
                append("으로 이동합니다.")
            }
        } else {
            binding.headerTitle.text = "분리수거함"
            binding.toastText.text = buildSpannedString {
                append("작성된 글은 ")
                color(getColor(R.color.blue_3)) {
                    append("분리수거함")
                }
                append("으로 이동합니다.")
            }
        }
    }

    private fun configureDropNavigation() {
        binding.back.setOnClickListener {
            (activity as MainActivity).popHomeDrop()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popHomeDrop()
        }
    }
//
//    private fun configureToastText() {
//        binding.toastText.text = buildSpannedString {
//            color(getColor(R.color.blue_3)) {
//                append("버린 기록")
//            }
//            append("은 보관함으로 이동합니다.")
//        }
//    }

    companion object {
        fun newInstance(isDelete: Boolean) = HomeDropFragment().apply {
            arguments = bundleOf("isDelete" to isDelete)
        }
    }
}