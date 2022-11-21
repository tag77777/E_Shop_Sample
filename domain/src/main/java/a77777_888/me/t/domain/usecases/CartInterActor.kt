package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.IProductsSet

class CartInterActor(private val productsSet: IProductsSet) {
    fun addCartItem(item: IBestSellerItem) = productsSet.addItem(item)
    fun removeCartItem(item: IBestSellerItem) = productsSet.removeItem(item)
    fun getCartSet() = productsSet.getSet()
}