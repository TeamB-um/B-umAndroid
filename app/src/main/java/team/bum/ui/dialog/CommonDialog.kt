package team.bum.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import team.bum.R
import team.bum.databinding.DialogCommonBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.util.setVisible
import kotlin.math.roundToInt

class CommonDialog : BaseDialogFragment<DialogCommonBinding>() {

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
    private val showEdit: Boolean
        get() = arguments?.getBoolean("showEdit") ?: false
    private val clickListener: ClickListener?
        get() = if (parentFragment == null) (activity as? ClickListener) else (parentFragment as? ClickListener)

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogCommonBinding.inflate(inflater, container, false)

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
            binding.btnCancel.setVisible()
            binding.btnCancel.text = btnCancelText
            binding.btnCancel.setOnClickListener {
                clickListener?.onClickCancel()
                dismiss()
            }
        }
        if (showClose) {
            binding.close.setVisible()
            binding.close.setOnClickListener {
                clickListener?.onClickClose()
                dismiss()
            }
        }
        if (showEdit) {
            binding.body.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topMargin = (resources.displayMetrics.density * 16).roundToInt()
            }
            binding.btn.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topMargin = (resources.displayMetrics.density * 84).roundToInt()
            }
            binding.btn.setOnClickListener {
                binding.enter.text.toString().let { clickListener?.onClickYes(it) }
                dismiss()
            }
            binding.enter.apply {
                setVisible()
                addTextChangedListener {
                    if (it?.length == 6) setBackgroundResource(R.drawable.bg_edit_border_error)
                    else setBackgroundResource(R.drawable.bg_edit_border)
                }
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
        fun onClickYes(text: String) {}
        fun onClickCancel() {}
        fun onClickClose() {}
    }

    companion object {
        fun newInstance(
            title: String? = null,
            body: String? = null,
            btnText: String? = null,
            showCancel: Boolean = false,
            btnCancelText: String? = null,
            showClose: Boolean = false,
            showEdit: Boolean = false,
        ) = CommonDialog().apply {
            arguments = bundleOf(
                "title" to title,
                "body" to body,
                "btnText" to btnText,
                "showCancel" to showCancel,
                "btnCancelText" to btnCancelText,
                "showClose" to showClose,
                "showEdit" to showEdit,
            )
        }
    }
}