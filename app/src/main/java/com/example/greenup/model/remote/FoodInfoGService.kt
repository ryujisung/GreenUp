import com.example.greenup.model.remote.FoodInfoGData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodInfoGService {
    @GET("index.do")
    fun getImages(@Query("serviceKey") serviceKey: String,
                  @Query("pageNo") pageNo: Int,
                  @Query("cntPerPage") cntPerPage: Int): Call<FoodInfoGData>
}
