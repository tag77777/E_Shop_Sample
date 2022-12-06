package a77777_888.me.t.ecommercesample.presentation.model.phone

import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.IHomeStoreItem
import a77777_888.me.t.domain.model.IPhones

data class UiPhones(
    override val bestSeller: List<IBestSellerItem>,
    override val homeStore: List<IHomeStoreItem>
) : IPhones {
    constructor(phones: IPhones): this(
        bestSeller = phones.bestSeller,
        homeStore = phones.homeStore
    )
}
