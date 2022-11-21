package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.IProductsSet

class FavoritesInterActor(private val productsSet: IProductsSet) {
    fun addFavoritesItem(item: IBestSellerItem) = productsSet.addItem(item)
    fun removeFavoritesItem(item: IBestSellerItem) = productsSet.removeItem(item)
    fun getFavoritesSet() = productsSet.getSet()
}