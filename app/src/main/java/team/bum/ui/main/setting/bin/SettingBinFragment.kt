package team.bum.ui.main.setting.bin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import team.bum.R
import team.bum.databinding.FragmentSettingBinBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity

class SettingBinFragment : BaseFragment<FragmentSettingBinBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingBinBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureSettingNavigation()
    }

    private fun configureSettingNavigation() {
        binding.imageBack.setOnClickListener {
            (activity as MainActivity).popSetting()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popSetting()
        }
    }
}