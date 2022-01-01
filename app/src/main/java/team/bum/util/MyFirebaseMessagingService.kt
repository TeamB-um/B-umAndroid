package team.bum.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.Call
import team.bum.R
import team.bum.api.ServiceCreator
import team.bum.api.data.RequestPushToken
import team.bum.api.data.ResponsePushToken
import team.bum.ui.main.push.PushActivity

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

        val resultIntent = Intent(this, PushActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
//        val intent = Intent(this,PushActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // 액티비티 중복 생성 방지

//        val pendingIntent = PendingIntent.getActivity(this, 0 , intent,
//            PendingIntent.FLAG_CANCEL_CURRENT) // 일회성


        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 소리
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                notificationBuilder.apply {
                    setSmallIcon(R.mipmap.ic_launcher)
                    setContentTitle(title) // 제목
                    setContentText(body) // 내용
                    setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    setAutoCancel(true)
                    setSound(defaultSoundUri)
                    setContentIntent(resultPendingIntent)
                }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 예외처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        }

        notificationManager.notify(0 , notificationBuilder.build()) // 알림 생성
    }


    companion object {
        private const val TAG = "MyFirebaseMsgService"
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji party를 위한 채널 "
        private const val CHANNEL_ID = "Channel_Id"
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