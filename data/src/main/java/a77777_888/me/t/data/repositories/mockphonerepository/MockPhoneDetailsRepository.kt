package a77777_888.me.t.data.repositories.mockphonerepository

import a77777_888.me.t.data.remote.mockrepository.MockDataRepository
import a77777_888.me.t.domain.model.IProductDetails
import a77777_888.me.t.domain.repositories.IProductDetailsRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class MockPhoneDetailsRepository @Inject constructor() : IProductDetailsRepository {

    override suspend fun getPhoneDetails(): IProductDetails {
        delay(Random.nextLong(1000))
        return MockDataRepository.phoneDetailsResponse!!
    }

}

