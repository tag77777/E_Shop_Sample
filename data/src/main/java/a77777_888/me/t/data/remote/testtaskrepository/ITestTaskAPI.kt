package a77777_888.me.t.data.remote.testtaskrepository

import a77777_888.me.t.data.remote.entities.PhoneDetailsResponse
import a77777_888.me.t.data.remote.entities.PhonesResponse
import retrofit2.http.GET

interface ITestTaskAPI {

    @GET("/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getProducts(): PhonesResponse

    @GET("/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductDetails() : PhoneDetailsResponse

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3"
    }
}
