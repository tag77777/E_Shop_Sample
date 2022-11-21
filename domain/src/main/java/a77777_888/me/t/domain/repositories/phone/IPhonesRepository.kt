package a77777_888.me.t.domain.repositories.phone

import a77777_888.me.t.domain.model.IPhones
import a77777_888.me.t.domain.model.LoadResult
import kotlinx.coroutines.flow.Flow

interface IPhonesRepository {
    fun phones(): Flow<LoadResult<IPhones>>
}