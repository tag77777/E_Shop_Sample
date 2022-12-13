package a77777_888.me.t.data.repositories.testtaskphonerepository

import a77777_888.me.t.data.remote.testtaskrepository.TestTaskSourceProvider
import a77777_888.me.t.domain.model.*
import a77777_888.me.t.domain.repositories.IProductDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//class TestTaskPhoneDetailsRepository(
//    private val testTaskSourceProvider: TestTaskSourceProvider
//) : IPhoneDetailsRepository {
//
//    override fun getPhoneDetails(): Flow<LoadResult<IPhoneDetails>> =
//        wrapToFlowForTestTask {
//            testTaskSourceProvider.api.getPhoneDetails()
//        }
//}

class TestTaskPhoneDetailsRepository(
    private val testTaskSourceProvider: TestTaskSourceProvider
) : IProductDetailsRepository {

    override suspend fun getPhoneDetails(): IProductDetails {
        return testTaskSourceProvider.api.getPhoneDetails()
    }

}

fun <T> wrapToFlowForTestTask(block: suspend () -> T): Flow<LoadResult<T>> =
    flow {
        emit(PendingLoadResult())
        try{
            emit(SuccessLoadResult(block()))
        } catch (e: Exception) {
            emit(ErrorLoadResult(e))
    }
}