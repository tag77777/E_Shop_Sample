package a77777_888.me.t.data.repositories

import a77777_888.me.t.data.remote.testtaskrepository.entities.BestSeller
import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.IProductsSet

class CartRepository : IProductsSet {

    private val cartSet by lazy(LazyThreadSafetyMode.NONE) { setOf<BestSeller>() }

    override fun addItem(item: IBestSellerItem) {
        cartSet + item
    }

    override fun removeItem(item: IBestSellerItem) {
        cartSet - item
    }

    override fun getSet(): Set<BestSeller> {
        return cartSet
    }
}