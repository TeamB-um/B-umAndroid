package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import retrofit2.Call
import team.bum.R
import team.bum.api.data.ResponseCategory
import team.bum.api.data.ResponseStats
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.DialogStatsBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.dialog.adapter.StatsDialogAdapter
import team.bum.ui.dialog.data.StatsMonthInfo
import team.bum.ui.dialog.data.StatsTotalInfo
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class StatsDialog : BaseDialogFragment<DialogStatsBinding>() {
    private val statsDialogAdapter = StatsDialogAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogStatsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()

        binding.imageClose.setOnClickListener { dismiss() }
        binding.recyclerMonthStats.adapter = statsDialogAdapter
        binding.recyclerTotalStats.adapter = statsDialogAdapter
        getStatsInfo()
    }

    private fun setupDialogBackground() {
        val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
        val dialogHorizontalMargin = (Resources.getSystem().displayMetrics.density * 16) * 2
        dialog!!.window!!.apply {
            setLayout(
                (deviceWidth - dialogHorizontalMargin).toInt(),
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawableResource(R.drawable.bg_dialog_common)
        }
    }

    private fun getStatsInfo() {
        val call: Call<ResponseStats> = ServiceCreator.bumService.getStats(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                statsDialogAdapter.setMonthItems(it.data.monthStat)
                statsDialogAdapter.setTotalItems(it.data.allStat)
            }
        )
    }

    class CustomDialogBuilder {
        private val dialog = StatsDialog()

        fun create(): StatsDialog {
            return dialog
        }
    }
}