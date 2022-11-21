package a77777_888.me.t.domain.model

interface IBestSellerItem {
    val discountPrice: Int
    val id: Int
    val isFavorites: Boolean
    val picture: String
    val priceWithoutDiscount: Int
    val title: String
}