package a77777_888.me.t.data.repositories.mockphonerepository

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.domain.model.IProducts
import a77777_888.me.t.domain.repositories.IProductsRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class MockPhonesRepository : IProductsRepository {

    override suspend fun products(): IProducts {
        delay(Random.nextLong(1500))
        return MockDataRepository.phonesResponse as IProducts
    }

}

