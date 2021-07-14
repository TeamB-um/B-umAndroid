package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import team.bum.R
import team.bum.databinding.DialogRewardBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.author
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.content
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.date
import team.bum.ui.main.archive.reward.ArchiveRewardFragment.Companion.sentence

class RewardDialog : BaseDialogFragment<DialogRewardBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogRewardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()

        binding.tvPopupDate.text = date
        binding.tvPopupContent.text = sentence
        binding.tvPopupAuthor.text = author
        binding.tvPopupComment.text = content
        binding.ivClose.setOnClickListener { dismiss() }
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