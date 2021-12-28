package team.bum.ui.main.push

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import team.bum.databinding.ActivityPushBinding

class PushActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPushBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDialog()
    }

    private fun initDialog() {
        val dialog = PushDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "dialog")
    }
}