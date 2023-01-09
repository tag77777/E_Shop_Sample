package a77777_888.me.t.data.repositories.testtaskphonerepository

import a77777_888.me.t.data.remote.testtaskrepository.TestTaskSourceProvider
import a77777_888.me.t.domain.model.IProducts
import a77777_888.me.t.domain.repositories.IProductsRepository

class TestTaskPhonesRepository(
    private val testTaskSourceProvider: TestTaskSourceProvider
) : IProductsRepository {

    override suspend fun products(): IProducts {
        return testTaskSourceProvider.api.getProducts()
    }
}

