package team.bum.ui.main.setting.bin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import team.bum.R
import team.bum.databinding.FragmentSettingBinBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.setting.adapter.SettingBinListAdapter
import team.bum.ui.main.setting.data.BinListInfo

class SettingBinFragment : BaseFragment<FragmentSettingBinBinding>() {
    private val settingBinListAdapter = SettingBinListAdapter()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingBinBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerBinList.layoutManager = LinearLayoutManager(activity)
        binding.recyclerBinList.adapter = settingBinListAdapter
        addBinInfo()
        totalNumberEvent()
        configureSettingNavigation()

        binding.imageCheck.setOnClickListener {
            CommonDialog.newInstance(
                "분리수거함 추가",
                "분리수거함을 추가해 스트레스를 분류하세요.",
                "확인", true, "취소", true, showEdit=true
            ).show(childFragmentManager, null)
        }
    }

    private fun addBinInfo() {
        settingBinListAdapter.setItems(
            listOf<BinListInfo>(
                BinListInfo(
                    category = "취업"
                ),
                BinListInfo(
                    category = "학업"
                ),
                BinListInfo(
                    category = "인간관계"
                ),
                BinListInfo(
                    category = "건강"
                ),
                BinListInfo(
                    category = "금전"
                ),
                BinListInfo(
                    category = "개인"
                )
            )
        )
    }

    private fun totalNumberEvent() {
        binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
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