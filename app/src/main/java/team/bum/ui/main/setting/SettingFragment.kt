package team.bum.ui.main.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.bum.R
import team.bum.databinding.FragmentSettingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetEvent()
        configureSettingBinNavigation()
    }

    private fun bottomSheetEvent() {
        binding.layoutDeleteDuration.setOnClickListener {
            val sheetFragment: SheetFragment = SheetFragment()
            sheetFragment.show(requireActivity().supportFragmentManager, sheetFragment.tag)
        }
    }

    private fun configureSettingBinNavigation() {
        binding.layoutTrashManagement.setOnClickListener {
            (activity as MainActivity).navigateSettingToManagement()
        }
    }

    //    override fun onClicked(text: String) {
//        binding.tvDurationDay.text = text
//    }

}