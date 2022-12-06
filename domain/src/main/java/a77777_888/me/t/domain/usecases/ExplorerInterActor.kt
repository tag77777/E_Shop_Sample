package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.IPhones
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.model.SuccessLoadResult
import a77777_888.me.t.domain.repositories.phone.IPhonesRepository

class ExplorerInterActor (private val iPhonesRepository: IPhonesRepository) {
    suspend fun phones(): LoadResult<IPhones> = SuccessLoadResult(iPhonesRepository.phones())
    fun <T> tablets(): LoadResult<T>? = null
    fun <T> tvSets(): LoadResult<T>? = null
    fun <T> computers(): LoadResult<T>? = null
    fun <T> tools(): LoadResult<T>? = null
    fun <T> autoAccessories(): LoadResult<T>? = null
    fun <T> books(): LoadResult<T>? = null
}