package a77777_888.me.t.data.repositories.mockphonerepository

import a77777_888.me.t.domain.model.IBestSellerItem
import a77777_888.me.t.domain.model.IHomeStoreItem
import a77777_888.me.t.domain.model.IProducts
import a77777_888.me.t.domain.repositories.IProductsRepository
import kotlinx.coroutines.delay
import kotlin.random.Random

class EmptyListsMockRepository : IProductsRepository {
    override suspend fun products(): IProducts {
        delay(Random.nextLong(1500))
        return object : IProducts{
            override val bestSeller: List<IBestSellerItem>
                get() = listOf()
            override val homeStore: List<IHomeStoreItem>
                get() = listOf()
        }
    }
}