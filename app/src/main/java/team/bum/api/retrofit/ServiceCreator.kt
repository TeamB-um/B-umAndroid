package team.bum.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.bum.api.service.BiumService

object ServiceCreator {
    private const val BASE_URL = "http://"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val biumService: BiumService = retrofit.create(BiumService::class.java)
}