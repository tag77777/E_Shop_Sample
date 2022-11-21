package a77777_888.me.t.data.remote.mockrepository

import a77777_888.me.t.data.remote.testtaskrepository.entities.PhoneDetailsResponse
import a77777_888.me.t.data.remote.testtaskrepository.entities.PhonesResponse
import android.content.Context
import com.google.gson.Gson

object MockDataRepository {

    var phoneDetailsResponse: PhoneDetailsResponse? = null
        private set

    var phonesResponse: PhonesResponse? = null
        private set

    @Throws(Exception::class)
    fun init(context: Context) {
        phoneDetailsResponse = getClassFromJsonFile(
            context,
            "json/phone_details.json",
            PhoneDetailsResponse::class.java
        )
        phonesResponse = getClassFromJsonFile(
            context,
            "json/phones.json",
            PhonesResponse::class.java
        )

    }

    @Throws(Exception::class)
    private fun <T>getClassFromJsonFile(
        context: Context,
        fileName: String,
        classOfT: Class<T>
    ): T {
        val jsonString = context.assets
            .open(fileName)
            .bufferedReader()
            .use { it.readText() }

        return Gson().fromJson(jsonString, classOfT)
    }
}