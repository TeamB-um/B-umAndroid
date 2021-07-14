package team.bum.api.service

import retrofit2.http.*
import team.bum.api.data.*
import retrofit2.Call

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

    @GET("writings")
    fun getWriting(
        @Header("x-auth-token") token: String,
    ): Call<ResponseMyWriting>

    @GET("trashcans")
    fun getTrashCans(
        @Header("x-auth-token") token: String
    ): Call<ResponseTrashCans>

    @GET("rewards")
    fun getReward(
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

    @POST("categories")
    fun setCategoryInfo(
        @Header("x-auth-token") token: String,
        @Body body: RequestCategory
    ): Call<ResponseCategory>

    @DELETE("categories/{category_id}")
    fun deleteCategoryInfo(
        @Header("x-auth-token") token: String,
        @Body body: RequestDeleteCategory
    ): Call<ResponseCategory>
}
