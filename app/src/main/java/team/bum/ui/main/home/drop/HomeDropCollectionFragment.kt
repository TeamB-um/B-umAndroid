package team.bum.ui.main.home.drop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import team.bum.R
import team.bum.databinding.FragmentHomeDropCollectionBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.util.getColor

class HomeDropCollectionFragment : BaseFragment<FragmentHomeDropCollectionBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeDropCollectionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureDropNavigation()
        configureToastText()
    }

    private fun configureDropNavigation() {
        binding.back.setOnClickListener {
            (activity as MainActivity).popHomeDropCollection()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popHomeDropCollection()
        }
    }

    private fun configureToastText() {
        binding.toastText.text = buildSpannedString {
            color(getColor(R.color.blue_3)) {
                append("버린 기록")
            }
            append("은 분리수거함으로 이동합니다.")
        }
    }
}