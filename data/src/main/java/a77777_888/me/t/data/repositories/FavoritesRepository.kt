package a77777_888.me.t.data.repositories

import a77777_888.me.t.domain.model.ProductItem
import a77777_888.me.t.domain.repositories.IFavoritesRepository
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "Ecommerce"

@Singleton
class FavoritesRepository @Inject constructor() : IFavoritesRepository {

    private val favoritesList by lazy(LazyThreadSafetyMode.NONE) {
        mutableListOf<ProductItem>()
    }

    override fun getItem(position: Int): ProductItem {
        return favoritesList[position]
    }

    override fun addItem(item: ProductItem) {
        favoritesList.add(item)
    }

    override fun removeItem(item: ProductItem) {
        favoritesList.remove(item)
    }

    override fun contains(item: ProductItem): Boolean {
        return favoritesList.contains(item)
    }

    override fun size(): Int {
        return favoritesList.size
    }

    override fun getById(id: String): ProductItem? =
        favoritesList.firstOrNull { it.id == id }

    override fun getList(): List<ProductItem> {
        return favoritesList.toList()
    }
}