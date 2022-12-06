package a77777_888.me.t.data.repositories

import a77777_888.me.t.domain.model.ProductItem
import a77777_888.me.t.domain.repositories.IFavoritesRepository
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "Ecommerce"

@Singleton
class FavoritesRepository @Inject constructor() : IFavoritesRepository {

    private val favoritesSet by lazy(LazyThreadSafetyMode.NONE) {
        Log.i(TAG, "Favorites repository created ")
        mutableSetOf<ProductItem>()
    }

    override fun getItem(position: Int): ProductItem {
        return favoritesSet.toList()[position]
    }

    override fun addItem(item: ProductItem) {
        favoritesSet.add(item)
        Log.i(TAG, "Favorites.addItem: size = ${favoritesSet.size}")
    }

    override fun removeItem(item: ProductItem) {
        favoritesSet.remove(item)
        Log.i(TAG, "Favorites.removeItem: size = ${favoritesSet.size}")
    }

    override fun contains(item: ProductItem): Boolean {
        return favoritesSet.contains(item)
    }

    override fun size(): Int {
        return favoritesSet.size
    }

    override fun getById(id: String): ProductItem? =
        favoritesSet.firstOrNull { it.id == id }

    override fun getList(): List<ProductItem> {
        return favoritesSet.toList()
    }
}