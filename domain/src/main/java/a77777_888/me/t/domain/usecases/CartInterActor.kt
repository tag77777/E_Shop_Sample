package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.CartItem
import a77777_888.me.t.domain.repositories.ICartRepository

class CartInterActor(private val iCartRepository: ICartRepository) {
    fun getItem(position: Int) = iCartRepository.getItem(position)
    fun addCartItem(item: CartItem) = iCartRepository.addItem(item)
    fun removeCartItem(item: CartItem) = iCartRepository.removeItem(item)
    fun contains(item: CartItem) = iCartRepository.contains(item)
    fun size() = iCartRepository.size()
    fun getTotalPrice() = iCartRepository.getTotalPrice()
    fun getList() = iCartRepository.getList()
}