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
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.colorIndex
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.content
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.date
import team.bum.ui.main.archive.writing.ArchiveWritingFragment.Companion.title
import team.bum.util.getColor

class WritingDialog : BaseDialogFragment<DialogWritingBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initWritingDialog()
        setupDialogBackground()
    }

    private fun initWritingDialog() {
        binding.apply {
            tvCategory.text = category
            tvTitle.text = title
            tvDate.text = date
            tvContent.text = content
            imageClose.setOnClickListener { dismiss() }
            when (colorIndex) {
                0 -> tvCategory.setTextColor(getColor(R.color.blue_2_main))
                1 -> tvCategory.setTextColor(getColor(R.color.green_3))
                2 -> tvCategory.setTextColor(getColor(R.color.pink_3))
                3 -> tvCategory.setTextColor(getColor(R.color.blue_4))
                4 -> tvCategory.setTextColor(getColor(R.color.green_5))
                5 -> tvCategory.setTextColor(getColor(R.color.green_2_main))
                6 -> tvCategory.setTextColor(getColor(R.color.blue_3))
                7 -> tvCategory.setTextColor(getColor(R.color.pink_2_main))
                else -> tvCategory.setTextColor(getColor(R.color.text_grey))
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