package a77777_888.me.t.data.repositories.mockphonerepository

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.domain.model.IPhones
import a77777_888.me.t.domain.repositories.phone.IPhonesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class MockPhonesRepository @Inject constructor(): IPhonesRepository{

    override suspend fun phones(): IPhones {
        delay(Random.nextLong(1500))
        return MockDataRepository.phonesResponse as IPhones
    }

}

