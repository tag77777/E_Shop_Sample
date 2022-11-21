package a77777_888.me.t.domain.model

interface IProductsSet {
    fun addItem(item: IBestSellerItem)
    fun removeItem(item: IBestSellerItem)
    fun getSet(): Set<IBestSellerItem>
}