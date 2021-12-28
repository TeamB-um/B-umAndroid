package team.bum.ui.main.push

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import retrofit2.Call
import team.bum.R
import team.bum.api.ServiceCreator
import team.bum.api.data.ResponseGift
import team.bum.databinding.DialogPushGiftBinding
import team.bum.ui.base.BaseDialogFragment
import team.bum.ui.main.MainActivity
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class PushGiftDialog: BaseDialogFragment<DialogPushGiftBinding>() {
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogPushGiftBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonClickEvent()
        getPushGift()
        setDialogBackground()
    }

    private fun getPushGift() {
        val call: Call<ResponseGift> = ServiceCreator.bumService.getGift(
            sharedPreferences.getValue("token", "")
        )

        call.enqueueUtil(
            onSuccess = {
                binding.body.text = it.data.present.sentence
            }
        )
    }

    private fun buttonClickEvent() {
        binding.close.setOnClickListener {
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
        private val dialog = PushGiftDialog()

        fun create(): PushGiftDialog {
            return dialog
        }
    }
}