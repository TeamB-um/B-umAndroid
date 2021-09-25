package team.bum.util

import android.app.*
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.Call
import team.bum.R
import team.bum.api.ServiceCreator
import team.bum.api.data.RequestPushToken
import team.bum.api.data.ResponsePushToken
import team.bum.ui.main.MainActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    sendRegistrationToServer(token)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if(remoteMessage.data.isNotEmpty()){
            sendNotification(remoteMessage.notification?.title,
                remoteMessage.notification?.body!!)
        }
        else{

        }
    }

    private fun sendNotification(title: String?, body: String){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // 액티비티 중복 생성 방지
        val pendingIntent = PendingIntent.getActivity(this, 0 , intent,
            PendingIntent.FLAG_ONE_SHOT) // 일회성

        val channelId = "channel" // 채널 아이디
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 소리
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title) // 제목
            .setContentText(body) // 내용
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 예외처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 , notificationBuilder.build()) // 알림 생성
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

    private fun sendRegistrationToServer(token: String){
        val body = RequestPushToken(token)
        val call: Call<ResponsePushToken> = ServiceCreator.bumService.getPushToken(body)
        call.enqueueUtil(
            onSuccess = {
                Log.d("tag", "pushTokenSuccess?: ${it.success}")
            }
        )
        Log.d("token", "sendToken: $$token")
    }
}