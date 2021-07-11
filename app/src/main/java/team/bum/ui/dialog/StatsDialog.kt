package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import team.bum.R
import team.bum.databinding.DialogStatsBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.dialog.adapter.StatsDialogAdapter
import team.bum.ui.dialog.data.StatsMonthInfo
import team.bum.ui.dialog.data.StatsTotalInfo

class StatsDialog : BaseDialogFragment<DialogStatsBinding>() {
    private val statsDialogAdapter = StatsDialogAdapter()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogStatsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()

        binding.imageClose.setOnClickListener { dismiss() }
        binding.recyclerMonthStats.adapter = statsDialogAdapter
        binding.recyclerTotalStats.adapter = statsDialogAdapter
        addStatsMonthInfo()
        addStatsTotalInfo()
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

    private fun addStatsMonthInfo() {
        statsDialogAdapter.setMonthItems(
            listOf<StatsMonthInfo>(
                StatsMonthInfo(
                    category = "인간관계",
                    percent = "35%"
                ),
                StatsMonthInfo(
                    category = "인간",
                    percent = "20%"
                ),
                StatsMonthInfo(
                    category = "관계",
                    percent = "10%"
                ),
                StatsMonthInfo(
                    category = "기타",
                    percent = "35%"
                )
            )
        )
    }

    private fun addStatsTotalInfo() {
        statsDialogAdapter.setTotalItems(
            listOf<StatsTotalInfo>(
                StatsTotalInfo(
                    category = "인간관계",
                    percent = "35%"
                ),
                StatsTotalInfo(
                    category = "인간",
                    percent = "20%"
                ),
                StatsTotalInfo(
                    category = "관계",
                    percent = "10%"
                ),
                StatsTotalInfo(
                    category = "기타",
                    percent = "35%"
                )
            )
        )
    }

    class CustomDialogBuilder {
        private val dialog = StatsDialog()

        fun create(): StatsDialog {
            return dialog
        }
    }
}