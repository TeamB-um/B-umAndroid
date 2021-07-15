package team.bum.api.service

import retrofit2.http.*
import team.bum.api.data.*
import retrofit2.Call
import team.bum.util.DateString

interface BumService {
    // USER
    @POST("users")
    fun getToken(@Body body: RequestSignIn): Call<ResponseToken>

    @GET("users")
    fun getUserInfo(
        @Header("x-auth-token") token: String
    ): Call<ResponseUserInfo>

    // WRITING
    @POST("writings")
    fun postWriting(
        @Header("x-auth-token") token: String,
        @Body body: RequestWriting
    ): Call<ResponseWriting>

    @GET("writings")
    fun getWriting(
        @Header("x-auth-token") token: String,
    ): Call<ResponseWriting>

    @GET("writings")
    fun filterWriting(
        @Header("x-auth-token") token: String,
        @Query("start_date") start_date: DateString,
        @Query("end_date") end_date: DateString,
        @Query("category_ids") category_ids: List<String>
    ): Call<ResponseWriting>

    @GET("writings/stat/graph")
    fun getStats(
        @Header("x-auth-token") token: String
    ): Call<ResponseStats>

    // TRASHCANS
    @GET("trashcans")
    fun getTrashCans(
        @Header("x-auth-token") token: String
    ): Call<ResponseTrashCans>

    // CATEGORIES
    @GET("categories")
    fun getCategory(
        @Header("x-auth-token") token: String
    ): Call<ResponseCategory>

    @GET("categories/{category_Id}/rewards")
    fun getCategoryRewards(
        @Header("x-auth-token") token: String,
        @Path("category_Id") categoryId: String
    ): Call<ResponseCategoryReward>

    @POST("categories")
    fun addCategory(
        @Header("x-auth-token") token: String,
        @Body body: RequestCategory
    ): Call<ResponseCategory>

    @DELETE("categories/{category_id}")
    fun deleteCategory(
        @Header("x-auth-token") token: String,
        @Path("category_id") category_id: String
    ): Call<ResponseCategory>

    @PATCH("categories/{category_id}")
    fun editCategory(
        @Header("x-auth-token") token: String,
        @Path("category_id") category_id: String,
        @Body body: RequestCategory
    ): Call<ResponseCategory>

    // REWARDS
    @GET("rewards")
    fun getReward(
        @Header("x-auth-token") token: String
    ): Call<ResponseRewards>
}
