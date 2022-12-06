package a77777_888.me.t.domain.repositories.phone

import a77777_888.me.t.domain.model.IPhoneDetails

interface IPhoneDetailsRepository {
    suspend fun getPhoneDetails(): IPhoneDetails
}