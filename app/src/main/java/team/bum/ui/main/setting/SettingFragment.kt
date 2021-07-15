package team.bum.ui.main.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import retrofit2.Call
import team.bum.api.data.ResponseUserInfo
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentSettingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.util.*

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private val sheetFragment: SheetFragment = SheetFragment()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bottomSheetEvent()
        configureSettingBinNavigation()
        setDuration()
        getUserInfoData()
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
                binding.tvDurationDay.text = date
            }
        })
    }

    private fun getUserInfoData() {
        val call: Call<ResponseUserInfo> = ServiceCreator.bumService.getUserInfo(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                sharedPreferences.apply {
                    Log.d("userInfo", "$it")
                    setValue("period", it.data.deletePeriod.toString())
                    setBooleanValue("isPush", it.data.isPush)
                }
            })
    }
}