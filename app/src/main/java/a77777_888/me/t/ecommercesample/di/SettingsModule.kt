package a77777_888.me.t.ecommercesample.di

import a77777_888.me.t.data.repositories.CartRepository
import a77777_888.me.t.data.repositories.FavoritesRepository
import a77777_888.me.t.data.repositories.mockphonerepository.MockPhoneDetailsRepository
import a77777_888.me.t.data.repositories.mockphonerepository.MockPhonesRepository
import a77777_888.me.t.domain.repositories.ICartRepository
import a77777_888.me.t.domain.repositories.IFavoritesRepository
import a77777_888.me.t.domain.repositories.IProductDetailsRepository
import a77777_888.me.t.domain.repositories.IProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SettingsModule {

    @Binds
    fun bindCartRepository(
        cartRepository: CartRepository
    ): ICartRepository

    @Binds
    fun bindFavoritesRepository(
        favoritesRepository: FavoritesRepository
    ): IFavoritesRepository

    @Binds
    fun bindPhoneDetailsRepository(
        repository: MockPhoneDetailsRepository
    ): IProductDetailsRepository

}

