package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.model.SuccessLoadResult
import a77777_888.me.t.domain.repositories.IProductDetailsRepository

class ProductDetailsUseCase(
    private val productDetailsRepository: IProductDetailsRepository
){
    suspend fun get() = SuccessLoadResult(productDetailsRepository.getPhoneDetails())
}