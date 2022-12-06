package a77777_888.me.t.ecommercesample.presentation.model.phone

import a77777_888.me.t.domain.model.IHomeStoreItem

data class UiHomeStoreItem(
    override val id: Int,
    override val isBuy: Boolean,
    override val isNew: Boolean?,
    override val picture: String,
    override val subtitle: String,
    override val title: String
) : IHomeStoreItem {
    constructor( iHomeStoreItem: IHomeStoreItem): this(
        id = iHomeStoreItem.id,
        isBuy = iHomeStoreItem.isBuy,
        isNew = iHomeStoreItem.isNew,
        picture = iHomeStoreItem.picture,
        subtitle = iHomeStoreItem.subtitle,
        title = iHomeStoreItem.title
    )
}
