package a77777_888.me.t.data.remote.entities

import a77777_888.me.t.domain.model.IProducts
import com.google.gson.annotations.SerializedName

data class PhonesResponse(
    @SerializedName("best_seller")
    override val bestSeller: List<BestSellerItem>,
    @SerializedName("home_store")
    override val homeStore: List<HomeStoreItem>
) : IProducts