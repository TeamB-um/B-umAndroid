package team.bum.ui.main.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import team.bum.databinding.FragmentSettingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private val sheetFragment: SheetFragment = SheetFragment()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bottomSheetEvent()
        configureSettingBinNavigation()
        setDuration()
    }

    private fun bottomSheetEvent() {
        binding.layoutDeleteDuration.setOnClickListener {
            sheetFragment.show(requireActivity().supportFragmentManager, sheetFragment.tag)
        }
    }

    private fun configureSettingBinNavigation() {
        binding.layoutTrashManagement.setOnClickListener {
            (activity as MainActivity).navigateSettingToManagement()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).showFinishToast()
        }
    }

    private fun setDuration() {
        sheetFragment.setClickYesListener(object : SheetFragment.ClickListener {
            override fun onClickYes(date: String) {
                Log.d("야", date)
                binding.tvDurationDay.text = date
            }
        })
    }
}