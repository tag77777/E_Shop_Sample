package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.IProducts
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.model.SuccessLoadResult
import a77777_888.me.t.domain.repositories.IProductsRepository

class ExplorerUseCase {
    companion object {
        suspend fun getData(iProductsRepository: IProductsRepository)
        : SuccessLoadResult<IProducts> =
            SuccessLoadResult(iProductsRepository.products())
    }
}