package team.bum.ui.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import retrofit2.Call
import team.bum.api.ServiceCreator
import team.bum.api.data.RequestUser
import team.bum.api.data.ResponseUser
import team.bum.databinding.FragmentSettingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.util.*

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureSettingBinNavigation()
    }

    private fun configureSettingBinNavigation() {
        binding.layoutTrashManagement.setOnClickListener {
            (activity as MainActivity).navigateSettingToManagement()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).showFinishToast()
        }
    }
}