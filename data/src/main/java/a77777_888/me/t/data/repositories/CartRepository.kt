package a77777_888.me.t.data.repositories

import a77777_888.me.t.domain.model.CartItem
import a77777_888.me.t.domain.repositories.ICartRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() : ICartRepository {

    private val cartList by lazy(LazyThreadSafetyMode.NONE) {
        mutableListOf<CartItem>()
    }

    override fun getItem(position: Int): CartItem {
        return cartList[position]
    }

    override fun addItem(item: CartItem) {
        cartList.add(item)
    }

    override fun removeItem(item: CartItem) {
        cartList.remove(item)
    }

    override fun contains(item: CartItem): Boolean {
        return cartList.contains(item)
    }

    override fun size(): Int {
        return cartList.size
    }

    override fun getTotalPrice(): Int {
        return cartList.sumOf { it.number * it.product.price }
    }

    override fun getList(): List<CartItem> {
        return cartList.toList()
    }
}