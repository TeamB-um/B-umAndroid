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
import team.bum.api.ServiceCreator
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
                    signIn(uuid)
                } else {
                    Log.d("tag", "UUID : $uuid")
                    // FiXME 테스트용
                    //  signIn(uuid)
                    MyApplication.mySharedPreferences.setValue(
                        "token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjBlZTgyMTZjMDljYjQ2MDRkNmUyZWU5In0sImlhdCI6MTYyNjI0MzYwNywiZXhwIjoxNjI2NjAzNjA3fQ.cHXNOUWs3p-DXpShrAY_8f6iqeY44VsQoHMMLfU0K7Q"
                    )
                    navigateMain()
                    finish()
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
            })
    }

    private fun navigateMain() = startActivity(Intent(this, MainActivity::class.java))
}