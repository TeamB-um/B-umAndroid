package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import team.bum.R
import team.bum.api.data.Stat
import team.bum.databinding.DialogStatsBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.dialog.adapter.StatsDialogAdapter
import team.bum.ui.main.collection.CollectionFragment.Companion.monthPercent
import team.bum.ui.main.collection.CollectionFragment.Companion.statsItems
import team.bum.ui.main.collection.CollectionFragment.Companion.allStatsItems
import team.bum.ui.main.collection.CollectionFragment.Companion.totalPercent

class StatsDialog : BaseDialogFragment<DialogStatsBinding>() {
    private val statsDialogAdapter = StatsDialogAdapter()
    private val statsSecondDialogAdapter = StatsDialogAdapter()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogStatsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()
        setStatsList()

        binding.imageClose.setOnClickListener { dismiss() }
        binding.recyclerMonthStats.adapter = statsDialogAdapter
        binding.recyclerTotalStats.adapter = statsSecondDialogAdapter
    }

    private fun setStatsList() {
        statsDialogAdapter.setItems(statsItems)
        statsSecondDialogAdapter.setItems(allStatsItems)
        statsDialogAdapter.addItems(
            listOf<Stat>(
                Stat(
                    index = 9,
                    name = "기타",
                    percent = monthPercent
                )
            )
        )
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

    class CustomDialogBuilder {
        private val dialog = StatsDialog()

        fun create(): StatsDialog {
            return dialog
        }
    }
}