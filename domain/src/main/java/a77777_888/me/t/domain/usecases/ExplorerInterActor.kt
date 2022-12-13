package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.IProducts
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.model.SuccessLoadResult
import a77777_888.me.t.domain.repositories.IProductsRepository

class ExplorerInterActor (private val iPhonesRepository: IProductsRepository) {
    suspend fun phones(): LoadResult<IProducts> = SuccessLoadResult(iPhonesRepository.products())
    suspend fun <T> tablets(): LoadResult<IProducts> = SuccessLoadResult(iPhonesRepository.products())
    suspend fun <T> tvSets(): LoadResult<T>? = null
    suspend fun <T> computers(): LoadResult<T>? = null
    suspend fun <T> tools(): LoadResult<T>? = null
    suspend fun <T> autoAccessories(): LoadResult<T>? = null
    suspend fun <T> books(): LoadResult<T>? = null
}