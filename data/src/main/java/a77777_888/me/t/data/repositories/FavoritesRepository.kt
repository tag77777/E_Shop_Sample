package a77777_888.me.t.data.repositories

import a77777_888.me.t.data.remote.testtaskrepository.entities.BestSeller
import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.IProductsSet

class FavoritesRepository : IProductsSet {

    private val favoritesSet by lazy(LazyThreadSafetyMode.NONE) { setOf<BestSeller>() }

    override fun addItem(item: IBestSellerItem) {
        favoritesSet + item
    }

    override fun removeItem(item: IBestSellerItem) {
        favoritesSet - item
    }

    override fun getSet(): Set<BestSeller> {
        return favoritesSet
    }
}