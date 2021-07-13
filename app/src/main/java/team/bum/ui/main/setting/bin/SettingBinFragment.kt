package team.bum.ui.main.setting.bin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import team.bum.api.data.RequestCategory
import team.bum.api.data.ResponseCategory
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentSettingBinBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.setting.adapter.SettingBinListAdapter
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class SettingBinFragment : BaseFragment<FragmentSettingBinBinding>(), CommonDialog.ClickListener {
    private val settingBinListAdapter = SettingBinListAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingBinBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerBinList.layoutManager = LinearLayoutManager(activity)
        binding.recyclerBinList.adapter = settingBinListAdapter
        getCategoryListInfo()
        totalNumberEvent()
        configureSettingNavigation()

        binding.imagePlus.setOnClickListener {
            CommonDialog.newInstance(
                "분리수거함 추가",
                "분리수거함을 추가해 스트레스를 분류하세요.",
                "확인", true, "취소", true, showEdit = true
            ).show(childFragmentManager, null)
        }
    }

    private fun getCategoryListInfo() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.getCategoryInfo(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                settingBinListAdapter.setItems(it.data.categories)
                binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
            }
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

    override fun onClickYes(text: String) {
        val requestCategory = RequestCategory(
            name = text
        )
        val call: Call<ResponseCategory> = ServiceCreator.bumService.setCategoryInfo(
            sharedPreferences.getValue("token", ""), requestCategory)
        call.enqueueUtil(
            onSuccess = {
                Log.d("test", it.success.toString())
            }
        )
    }
}