package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import team.bum.R
import team.bum.databinding.DialogRewardBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.author
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.bgColorIndex
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.content
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.date
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.sentence

class RewardDialog : BaseDialogFragment<DialogRewardBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogRewardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()
        initRewardDialog()
    }

    private fun initRewardDialog() {
        binding.apply {
            binding.tvPopupDate.text = date
            binding.tvPopupContent.text = sentence
            binding.tvPopupAuthor.text = author
            binding.tvPopupComment.text = content
            binding.ivClose.setOnClickListener { dismiss() }
            Log.d("tag-dd", bgColorIndex.toString())
            when (bgColorIndex) {
                0 -> viewReward.setBackgroundResource(R.drawable.gradient1)
                1 -> viewReward.setBackgroundResource(R.drawable.gradient5)
                2 -> viewReward.setBackgroundResource(R.drawable.gradient8)
                3 -> viewReward.setBackgroundResource(R.drawable.gradient3)
                4 -> viewReward.setBackgroundResource(R.drawable.gradient6)
                5 -> viewReward.setBackgroundResource(R.drawable.gradient4)
                6 -> viewReward.setBackgroundResource(R.drawable.gradient2)
                7 -> viewReward.setBackgroundResource(R.drawable.gradient7)
                else -> viewReward.setBackgroundResource(R.color.bg)
            }
        }
    }

    private fun setupDialogBackground() {
        val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
        val dialogHorizontalMargin = (Resources.getSystem().displayMetrics.density * 16) * 2
        dialog!!.window!!.apply {
            setLayout(
                (deviceWidth - dialogHorizontalMargin).toInt(),
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawableResource(R.drawable.bg_dialog_reward)
        }
    }

    class CustomDialogBuilder {
        private val dialog = RewardDialog()

        fun create(): RewardDialog {
            return dialog
        }
    }
}
