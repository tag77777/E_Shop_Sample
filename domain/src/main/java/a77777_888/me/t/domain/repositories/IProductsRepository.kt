package a77777_888.me.t.domain.repositories

import a77777_888.me.t.domain.model.IProducts

interface IProductsRepository {
    suspend fun products(): IProducts
}