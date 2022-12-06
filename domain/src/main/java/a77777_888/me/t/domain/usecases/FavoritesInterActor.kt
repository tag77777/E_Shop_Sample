package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.ProductItem
import a77777_888.me.t.domain.repositories.IFavoritesRepository

class FavoritesInterActor(private val iFavoritesRepository: IFavoritesRepository) {
    fun getItem(position: Int) = iFavoritesRepository.getItem(position)
    fun addItem(item: ProductItem) = iFavoritesRepository.addItem(item)
    fun removeItem(item: ProductItem) = iFavoritesRepository.removeItem(item)
    fun contains(item: ProductItem) = iFavoritesRepository.contains(item)
    fun getById(id: String) = iFavoritesRepository.getById(id)
    fun size(): Int = iFavoritesRepository.size()
    fun getList() = iFavoritesRepository.getList()
}