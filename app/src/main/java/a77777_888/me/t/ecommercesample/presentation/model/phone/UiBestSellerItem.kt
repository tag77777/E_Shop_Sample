package a77777_888.me.t.ecommercesample.presentation.model.phone

import a77777_888.me.t.domain.model.IBestSellerItem

data class UiBestSellerItem(
    override val discountPrice: Int,
    override val id: Int,
    override val isFavorites: Boolean,
    override val picture: String,
    override val priceWithoutDiscount: Int,
    override val title: String
) : IBestSellerItem {
    constructor(iBestSellerItem: IBestSellerItem): this(
        discountPrice = iBestSellerItem.discountPrice,
        id = iBestSellerItem.id,
        isFavorites = iBestSellerItem.isFavorites,
        picture = iBestSellerItem.picture,
        priceWithoutDiscount = iBestSellerItem.priceWithoutDiscount,
        title = iBestSellerItem.title
    )
}