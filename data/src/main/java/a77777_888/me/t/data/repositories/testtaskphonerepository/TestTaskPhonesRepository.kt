package a77777_888.me.t.data.repositories.testtaskphonerepository

import a77777_888.me.t.data.remote.testtaskrepository.TestTaskSourceProvider
import a77777_888.me.t.domain.model.IPhones
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.repositories.phone.IPhonesRepository
import kotlinx.coroutines.flow.Flow

//class TestTaskPhonesRepository(
//    private val testTaskSourceProvider: TestTaskSourceProvider
//) : IPhonesRepository {
//
//    override fun phones(): Flow<LoadResult<IPhones>> = wrapToFlowForTestTask {
//        testTaskSourceProvider.api.getPhones()
//    }
//}

class TestTaskPhonesRepository(
    private val testTaskSourceProvider: TestTaskSourceProvider
) : IPhonesRepository {

    override suspend fun phones(): IPhones {
        return testTaskSourceProvider.api.getPhones()
    }
}

