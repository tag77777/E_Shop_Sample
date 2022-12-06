package a77777_888.me.t.domain.model

data class ProductItem(
    val id: String,
    val title: String,
    val image: String,
    val price: Int
) {
    constructor(details: IPhoneDetails): this(
        id = details.id,
        title = details.title,
        image = details.images[0],
        price = details.price
    )

    constructor(item: IBestSellerItem): this(
        id = item.id.toString(),
        title = item.title,
        image = item.picture,
        price = item.discountPrice
    )
}
