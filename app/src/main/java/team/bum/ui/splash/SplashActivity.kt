package team.bum.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import team.bum.api.data.RequestSignIn
import team.bum.api.data.ResponseToken
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.ActivitySplashBinding
import team.bum.ui.main.MainActivity
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLottieListener()
    }

    private fun setLottieListener() {
        binding.lottie.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                val uuid = MyApplication.mySharedPreferences.getValue("uuid", "")
                if (uuid == "") {
                    MyApplication.mySharedPreferences.setValue("uuid", UUID.randomUUID().toString())
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
                MyApplication.mySharedPreferences.setValue("token", it.data.token)
                navigateMain()
                finish()
            })
    }

    private fun navigateMain() = startActivity(Intent(this, MainActivity::class.java))
}