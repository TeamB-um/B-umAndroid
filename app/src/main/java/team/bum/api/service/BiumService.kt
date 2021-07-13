package team.bum.api.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import team.bum.api.data.*

interface BiumService {
    @POST("users")
    fun getToken(@Body body: RequestSignIn): Call<ResponseToken>

    @GET("trashcans")
    fun getTrashCans(): Call<ResponseTrashCans>

    @GET("rewards")
    fun getRewards(): Call<ResponseRewards>

    @GET("categories/{categoryId}/rewards}")
    fun getCategoryRewards(
        @Path("categoryId") categoryId: String
    ): Call<ResponseCategoryReward>
}
