package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import retrofit2.Call
import team.bum.R
import team.bum.api.data.Stat
import team.bum.api.data.ResponseStats
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.DialogStatsBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.dialog.adapter.StatsDialogAdapter
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class StatsDialog : BaseDialogFragment<DialogStatsBinding>() {
    private val statsDialogAdapter = StatsDialogAdapter()
    private val statsSecondDialogAdapter = StatsDialogAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogStatsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()

        binding.imageClose.setOnClickListener { dismiss() }
        binding.recyclerMonthStats.adapter = statsDialogAdapter
        binding.recyclerTotalStats.adapter = statsSecondDialogAdapter
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
                var monthPercent: Int = 0
                var totalPercent: Int = 0
                statsDialogAdapter.setItems(
                    listOf<Stat>(
                        Stat(
                            index = it.data.stat[0].index,
                            name = it.data.stat[0].name,
                            percent = it.data.stat[0].percent
                        ),
                        Stat(
                            index = it.data.stat[1].index,
                            name = it.data.stat[1].name,
                            percent = it.data.stat[1].percent
                        ),
                        Stat(
                            index = it.data.stat[2].index,
                            name = it.data.stat[2].name,
                            percent = it.data.stat[2].percent
                        )
                    )
                )
                statsSecondDialogAdapter.setItems(
                    listOf<Stat>(
                        Stat(
                            index = it.data.allStat[0].index,
                            name = it.data.allStat[0].name,
                            percent = it.data.allStat[0].percent
                        ),
                        Stat(
                            index = it.data.allStat[1].index,
                            name = it.data.allStat[1].name,
                            percent = it.data.allStat[1].percent
                        ),
                        Stat(
                            index = it.data.allStat[2].index,
                            name = it.data.allStat[2].name,
                            percent = it.data.allStat[2].percent
                        )
                    )
                )
                for (i in 3 until it.data.stat.size) monthPercent += it.data.stat[i].percent
                Log.d("test", monthPercent.toString() + "monthpercent")
                statsDialogAdapter.addItems(
                    listOf<Stat>(
                        Stat(
                            index = 9,
                            name = "기타",
                            percent = monthPercent
                        )
                    )
                )
                for (i in 3 until it.data.allStat.size) totalPercent += it.data.allStat[i].percent
                Log.d("test", totalPercent.toString() + "totalpercent")
                statsSecondDialogAdapter.addItems(
                    listOf<Stat>(
                        Stat(
                            index = 9,
                            name = "기타",
                            percent = totalPercent
                        )
                    )
                )
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