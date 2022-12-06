package a77777_888.me.t.domain.repositories

import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.ProductItem

interface IFavoritesRepository {
    fun getItem(position: Int): ProductItem
    fun addItem(item: ProductItem)
    fun removeItem(item: ProductItem)
    fun contains(item: ProductItem): Boolean
    fun getById(id: String): ProductItem?
    fun size(): Int
    fun getList(): List<ProductItem>
}