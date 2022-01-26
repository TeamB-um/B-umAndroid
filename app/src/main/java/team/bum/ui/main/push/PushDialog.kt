package team.bum.ui.main.push

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import team.bum.R
import team.bum.databinding.DialogPushBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.main.MainActivity

class PushDialog : BaseDialogFragment<DialogPushBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogPushBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogBackground()
        buttonClickEvent()
    }

    private fun buttonClickEvent() {
        binding.btn.setOnClickListener {
            val dialog = PushGiftDialog.CustomDialogBuilder().create()
            dialog.isCancelable = false
            dialog.show(requireActivity().supportFragmentManager, "dialog")
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
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

    class CustomDialogBuilder {
        private val dialog = PushDialog()

        fun create(): PushDialog {
            return dialog
        }
    }
}