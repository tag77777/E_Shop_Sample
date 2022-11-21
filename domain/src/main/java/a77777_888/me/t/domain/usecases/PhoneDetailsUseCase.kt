package a77777_888.me.t.domain.usecases

import a77777_888.me.t.domain.repositories.phone.IPhoneDetailsRepository

class PhoneDetailsUseCase(
    private val phoneDetailsRepository: IPhoneDetailsRepository
){
    operator fun invoke() = phoneDetailsRepository.getPhoneDetails()
}