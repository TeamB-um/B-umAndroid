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

    private val sheetFragment: SheetFragment = SheetFragment()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        bottomSheetEvent()
        configureSettingBinNavigation()
//        setDuration()
    }

//    private fun bottomSheetEvent() {
//        binding.layoutDeleteDuration.setOnClickListener {
//            sheetFragment.show(requireActivity().supportFragmentManager, sheetFragment.tag)
//        }
//    }

    private fun configureSettingBinNavigation() {
        binding.layoutTrashManagement.setOnClickListener {
            (activity as MainActivity).navigateSettingToManagement()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).showFinishToast()
        }
    }

//    private fun setDuration() {
//        binding.tvDurationDay.text = "${sharedPreferences.getValue("period", "")}일"
//        sheetFragment.setClickYesListener(object : SheetFragment.ClickListener {
//            override fun onClickYes(date: String) {
//                binding.tvDurationDay.text = date
//                if (date == "즉시 삭제") editUserInfo(0)
//                else editUserInfo(Integer.parseInt(date.split("일")[0]))
//            }
//        })
//    }

    private fun editUserInfo(date: Int) {
        val call: Call<ResponseUser> = ServiceCreator.bumService.editUser(
            sharedPreferences.getValue("token", ""), RequestUser(date)
        )
        call.enqueueUtil(
            onSuccess = {
                sharedPreferences.apply {
                    setValue("period", it.data.user.delPeriod.toString())
                    setBooleanValue("isPush", it.data.user.isPush)
                }
            })
    }
}