package a77777_888.me.t.domain.repositories

import a77777_888.me.t.domain.model.IProductDetails

interface IProductDetailsRepository {
    suspend fun getPhoneDetails(): IProductDetails
}