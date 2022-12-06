package a77777_888.me.t.domain.repositories

import a77777_888.me.t.domain.model.CartItem

interface ICartRepository {
    fun getItem(position: Int): CartItem
    fun addItem(item: CartItem)
    fun removeItem(item: CartItem)
    fun contains(item: CartItem): Boolean
    fun size(): Int
    fun getTotalPrice(): Int
    fun getList(): List<CartItem>
}