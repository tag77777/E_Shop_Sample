package a77777_888.me.t.ecommercesample.presentation.detailsfragment

import a77777_888.me.t.domain.model.*
import a77777_888.me.t.domain.repositories.IProductDetailsRepository
import a77777_888.me.t.domain.usecases.ProductDetailsUseCase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    phoneDetailsRepository: IProductDetailsRepository
) : ViewModel() {
    private val phoneDetailsUseCase = ProductDetailsUseCase(phoneDetailsRepository)

    private val _loadResultFlow = MutableStateFlow<LoadResult<out Any>>(EmptyLoadResult())
    val loadResultFlow: StateFlow<LoadResult<out Any>> = _loadResultFlow

    fun getData() {
        _loadResultFlow.value = PendingLoadResult()

        viewModelScope.launch {
            _loadResultFlow.value = try {
                phoneDetailsUseCase.get()
            } catch (e: Exception) {
                ErrorLoadResult(e)
            }
        }
    }
}