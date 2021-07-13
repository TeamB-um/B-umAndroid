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

    @GET("trashcans")
    fun getTrashCans(): Call<ResponseTrashCans>

    @GET("rewards")
    fun getRewards(): Call<ResponseRewards>

    @GET("categories/{categoryId}/rewards}")
    fun getCategoryRewards(
        @Path("categoryId") categoryId: String
    ): Call<ResponseCategoryReward>
}
