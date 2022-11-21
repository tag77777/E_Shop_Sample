package a77777_888.me.t.data.repositories.mockphonerepository

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.domain.model.*
import a77777_888.me.t.domain.repositories.phone.IPhoneDetailsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class MockPhoneDetailsRepository: IPhoneDetailsRepository {

    override fun getPhoneDetails(): Flow<LoadResult<IPhoneDetails>> =
        wrapToFlowForMock(MockDataRepository.phoneDetailsResponse as IPhoneDetails)
}

fun <T> wrapToFlowForMock(item: T): Flow<LoadResult<T>> = flow {
    emit(PendingLoadResult())
    delay(Random.nextLong(3000))
    try{
        emit(SuccessLoadResult(item))
    } catch (e: Exception) {
        emit(ErrorLoadResult(e))
    }
}
