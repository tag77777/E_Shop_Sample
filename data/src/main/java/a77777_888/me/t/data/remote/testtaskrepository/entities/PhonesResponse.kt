package a77777_888.me.t.data.remote.testtaskrepository.entities

import a77777_888.me.t.domain.model.IPhones
import com.google.gson.annotations.SerializedName

data class PhonesResponse(
    @SerializedName("best_seller")
    override val bestSeller: List<BestSeller>,
    @SerializedName("home_store")
    override val homeStore: List<HomeStore>
) : IPhones