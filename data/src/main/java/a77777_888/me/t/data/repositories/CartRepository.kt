package a77777_888.me.t.data.repositories

import a77777_888.me.t.domain.model.CartItem
import a77777_888.me.t.domain.repositories.ICartRepository
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() : ICartRepository {

    private val cartSet by lazy(LazyThreadSafetyMode.NONE) {
        Log.i(TAG, "Cart repository created")
        mutableSetOf<CartItem>()
    }

    override fun getItem(position: Int): CartItem {
        return cartSet.toList()[position]
    }

    override fun addItem(item: CartItem) {
        cartSet.add(item)
        Log.i(TAG, "CartSet.addItem: size = ${cartSet.size}")
    }

    override fun removeItem(item: CartItem) {
        cartSet.remove(item)
        Log.i(TAG, "CartSet.remove: size = ${cartSet.size}")
    }

    override fun contains(item: CartItem): Boolean {
        return cartSet.contains(item)
    }

    override fun size(): Int {
        return cartSet.size
    }

    override fun getTotalPrice(): Int {
        return cartSet.sumOf { it.number * it.product.price }
    }

    override fun getList(): List<CartItem> {
        return cartSet.toList()
    }
}