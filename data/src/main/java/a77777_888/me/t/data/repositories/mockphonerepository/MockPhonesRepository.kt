package a77777_888.me.t.data.repositories.mockphonerepository

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.domain.model.IPhones
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.repositories.phone.IPhonesRepository
import kotlinx.coroutines.flow.Flow

class MockPhonesRepository : IPhonesRepository{

    override fun phones(): Flow<LoadResult<IPhones>> =
        wrapToFlowForMock(MockDataRepository.phonesResponse as IPhones)
}