package a77777_888.me.t.ecommercesample.presentation.explorerfragment

import a77777_888.me.t.domain.model.*
import a77777_888.me.t.domain.repositories.IProductsRepository
import a77777_888.me.t.domain.usecases.ExplorerInterActor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(
    phoneRepository: IProductsRepository,
): ViewModel() {
    private val explorerInterActor = ExplorerInterActor(phoneRepository)

    private val _loadResultFlow = MutableStateFlow<LoadResult<out Any>>(EmptyLoadResult())
    val loadResultFlow: StateFlow<LoadResult<out Any>> = _loadResultFlow


    fun getData() {
        _loadResultFlow.value = PendingLoadResult()

        viewModelScope.launch {
            _loadResultFlow.value = try {
                explorerInterActor.phones()
            } catch (e: Exception) {
                ErrorLoadResult(e)
            }
        }
    }
}