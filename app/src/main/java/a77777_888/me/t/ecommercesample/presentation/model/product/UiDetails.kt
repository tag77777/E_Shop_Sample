package a77777_888.me.t.ecommercesample.presentation.model.product

import a77777_888.me.t.domain.model.IProductDetails

data class UiDetails(
    override val cpu: String,
    override val camera: String,
    override val capacity: List<String>,
    override val color: List<String>,
    override var id: String,
    override var images: List<String>,
    override val isFavorites: Boolean,
    override var price: Int,
    override val rating: Double,
    override val sd: String,
    override val ssd: String,
    override var title: String
) : IProductDetails {

    constructor(details: IProductDetails): this(
        cpu = details.cpu,
        camera = details.camera,
        capacity = details.capacity,
        color = details.color,
        id = details.id,
        images = details.images,
        isFavorites = details.isFavorites,
        price = details.price,
        rating = details.rating,
        sd = details.sd,
        ssd = details.ssd,
        title = details.title
    )
}