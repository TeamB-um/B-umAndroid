package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import team.bum.R
import team.bum.databinding.DialogWritingBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.category
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.content
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.date
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.title

class WritingDialog : BaseDialogFragment<DialogWritingBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialogBackground()

        binding.tvCategory.text = category
        binding.tvTitle.text = title
        binding.tvDate.text = date
        binding.tvContent.text = content
        binding.imageClose.setOnClickListener { dismiss() }
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
        private val dialog = WritingDialog()

        fun create(): WritingDialog {
            return dialog
        }
    }
}