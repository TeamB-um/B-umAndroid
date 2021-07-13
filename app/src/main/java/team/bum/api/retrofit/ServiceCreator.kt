package team.bum.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.bum.api.service.BumService

object ServiceCreator {
    private const val BASE_URL = "http://3.34.253.79:5000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bumService: BumService = retrofit.create(BumService::class.java)
}