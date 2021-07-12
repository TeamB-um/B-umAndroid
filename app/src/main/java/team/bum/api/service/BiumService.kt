package team.bum.api.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import team.bum.api.data.ResponseCategoryReward
import team.bum.api.data.ResponseRewards
import team.bum.api.data.ResponseTrashCans

interface BiumService {
    @GET("trashcans")
    fun getTrashCans(): Call<ResponseTrashCans>

    @GET("rewards")
    fun getRewards(): Call<ResponseRewards>

    @GET("categories/{categoryId}/rewards}")
    fun getCategoryRewards(
        @Path("categoryId") categoryId: String
    ): Call<ResponseCategoryReward>
}
