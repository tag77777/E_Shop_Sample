package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.IPhones
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.repositories.phone.IPhonesRepository
import kotlinx.coroutines.flow.Flow

class ExplorerInterActor(
    private val iPhonesRepository: IPhonesRepository
) {
    fun phones(): Flow<LoadResult<IPhones>> = iPhonesRepository.phones()
    fun <T> tablets(): T? = null
    fun <T> tvSets(): T? = null
    fun <T> computers(): T? = null
    fun <T> tools(): T? = null
    fun <T> autoAccessories(): T? = null
    fun <T> books(): T? = null
}