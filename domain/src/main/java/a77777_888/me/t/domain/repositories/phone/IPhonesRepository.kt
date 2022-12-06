package a77777_888.me.t.domain.repositories.phone

import a77777_888.me.t.domain.model.IPhones

interface IPhonesRepository {
    suspend fun phones(): IPhones
}