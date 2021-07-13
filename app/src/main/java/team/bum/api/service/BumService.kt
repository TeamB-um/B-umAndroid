package team.bum.api.service

import retrofit2.Call
import retrofit2.http.*
import team.bum.api.data.*

interface BumService {
    @POST("users")
    fun getToken(@Body body: RequestSignIn): Call<ResponseToken>

    @GET("users")
    fun getUserInfo(
        @Header("x-auth-token") token: String
    ): Call<ResponseUserInfo>

    @POST("writings")
    fun postWriting(
        @Header("x-auth-token") token: String,
        @Body body: RequestWriting
    ): Call<ResponseWriting>

    @GET("trashcans")
    fun getTrashCans(
        @Header("x-auth-token") token: String
    ): Call<ResponseTrashCans>

    @GET("rewards")
    fun getRewards(
        @Header("x-auth-token") token: String
    ): Call<ResponseRewards>

    @GET("categories/{category_Id}/rewards")
    fun getCategoryRewards(
        @Header("x-auth-token") token: String,
        @Path("category_Id") categoryId: String
    ): Call<ResponseCategoryReward>

    @GET("categories")
    fun getCategory(
        @Header("x-auth-token") token: String
    ): Call<ResponseCategory>
}
