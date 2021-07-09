package team.bum.ui.main.home.drop

import android.app.Activity
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
import team.bum.util.StatusBarUtil
import team.bum.util.getColor

class HomeDropFragment : BaseFragment<FragmentHomeDropBinding>() {

    private val isDelete
        get() = arguments?.getBoolean("isDelete") ?: false

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeDropBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDrop()
        configureDropNavigation()
    }

    private fun initDrop() {
        if (isDelete) {
            binding.root.setBackgroundResource(R.drawable.bg_home_delete)
            binding.headerTitle.text = "삭제 휴지통"
            binding.toastText.text = buildSpannedString {
                append("작성된 글은 ")
                color(getColor(R.color.blue_3)) {
                    append("삭제 휴지통")
                }
                append("으로 이동합니다.")
            }
        } else {
            binding.root.setBackgroundResource(R.drawable.bg_home_collection)
            binding.headerTitle.text = "분리수거함"
            binding.toastText.text = buildSpannedString {
                append("작성된 글은 ")
                color(getColor(R.color.blue_3)) {
                    append("분리수거함")
                }
                append("으로 이동합니다.")
            }
        }
        StatusBarUtil.changeColor(context as Activity, getColor(R.color.main_statusbar))
    }

    private fun configureDropNavigation() {
        binding.back.setOnClickListener {
            (activity as MainActivity).popHomeDrop()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popHomeDrop()
        }
    }

    companion object {
        fun newInstance(isDelete: Boolean) = HomeDropFragment().apply {
            arguments = bundleOf("isDelete" to isDelete)
        }
    }
}