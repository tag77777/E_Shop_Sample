package a77777_888.me.t.data.remote.testtaskrepository.entities

import a77777_888.me.t.domain.model.IBestSellerItem
import com.google.gson.annotations.SerializedName

data class BestSeller(
    @SerializedName("discount_price")
    override val discountPrice: Int,
    override val id: Int,
    @SerializedName("is_favorites")
    override val isFavorites: Boolean,
    override val picture: String,
    @SerializedName("price_without_discount")
    override val priceWithoutDiscount: Int,
    override val title: String
) : IBestSellerItem