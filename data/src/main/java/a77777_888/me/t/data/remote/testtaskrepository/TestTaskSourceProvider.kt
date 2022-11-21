package a77777_888.me.t.data.remote.testtaskrepository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestTaskSourceProvider {
    val api: ITestTaskAPI = Retrofit.Builder()
        .baseUrl(ITestTaskAPI.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ITestTaskAPI::class.java)
}