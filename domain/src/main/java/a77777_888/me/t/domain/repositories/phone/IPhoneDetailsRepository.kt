package a77777_888.me.t.domain.repositories.phone

import a77777_888.me.t.domain.model.IPhoneDetails
import a77777_888.me.t.domain.model.LoadResult
import kotlinx.coroutines.flow.Flow

interface IPhoneDetailsRepository {
    fun getPhoneDetails(): Flow<LoadResult<IPhoneDetails>>
}