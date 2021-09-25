package team.bum.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import team.bum.api.ServiceCreator
import team.bum.api.data.*
import team.bum.databinding.ActivitySplashBinding
import team.bum.ui.main.MainActivity
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLottieListener()
        getUserInfo()
    }

    private fun setLottieListener() {
        binding.lottie.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                val uuid = sharedPreferences.getValue("uuid", "")
                if (uuid == "") {
                    sharedPreferences.setValue("uuid", UUID.randomUUID().toString())
                    signIn(UUID.randomUUID().toString())
                } else {
                    Log.d("tag", "UUID : $uuid")
                    signIn(uuid)
                }
            }
        })
    }

    private fun signIn(uuid: String) {
        val body = RequestSignIn(uuid)
        val call: Call<ResponseToken> = ServiceCreator.bumService.getToken(body)
        call.enqueueUtil(
            onSuccess = {
                Log.d("tag", "token : ${it.data.token}")
                sharedPreferences.setValue("token", it.data.token)
                navigateMain()
                finish()
            })
    }

    private fun getUserInfo() {
        val call: Call<ResponseUser> = ServiceCreator.bumService.getUser(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                sharedPreferences.apply {
                    setValue("period", it.data.user.delPeriod.toString())
                    setBooleanValue("isPush", it.data.user.isPush)
                }
            })
    }

    private fun navigateMain() = startActivity(Intent(this, MainActivity::class.java))
}