package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import team.bum.R
import team.bum.databinding.DialogCommonBinding
import team.bum.ui.base.BaseDialogFragment
import kotlin.math.roundToInt

class CommonDialog : BaseDialogFragment<DialogCommonBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogCommonBinding.inflate(inflater, container, false)

    private val title: String
        get() = arguments?.getString("title") ?: ""
    private val body: String
        get() = arguments?.getString("body") ?: ""
    private val btnText: String
        get() = arguments?.getString("btnText") ?: ""
    private val showCancel: Boolean
        get() = arguments?.getBoolean("showCancel") ?: false
    private val btnCancelText: String
        get() = arguments?.getString("btnCancelText") ?: ""
    private val showClose: Boolean
        get() = arguments?.getBoolean("showClose") ?: false
    private val clickListener: ClickListener?
        get() = if (parentFragment == null) (activity as? ClickListener) else (parentFragment as? ClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isCancelable = false
        binding.title.text = title
        binding.body.text = body
        binding.btn.text = btnText
        binding.btn.setOnClickListener {
            clickListener?.onClickYes()
            dismiss()
        }
        if (showCancel) {
            binding.btn.updateLayoutParams<ConstraintLayout.LayoutParams> {
                leftMargin = (resources.displayMetrics.density * 8).roundToInt()
            }
            binding.btnCancel.isVisible = true
            binding.btnCancel.text = btnCancelText
            binding.btnCancel.setOnClickListener {
                clickListener?.onClickNo()
                dismiss()
            }
        }
        if (showClose) {
            binding.close.isVisible = true
            binding.btnCancel.setOnClickListener {
                clickListener?.onClickNo()
                dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setDialogBackground()
    }

    private fun setDialogBackground() {
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

    interface ClickListener {
        fun onClickYes() {}
        fun onClickNo() {}
    }

    companion object {
        fun newInstance(
            title: String? = null,
            body: String? = null,
            btnText: String? = null,
            showCancel: Boolean = false,
            btnCancelText: String? = null,
            showClose: Boolean = false
        ) = CommonDialog().apply {
            arguments = bundleOf(
                "title" to title,
                "body" to body,
                "btnText" to btnText,
                "showCancel" to showCancel,
                "btnCancelText" to btnCancelText,
                "showClose" to showClose
            )
        }
    }
}