package a77777_888.me.t.ecommercesample.presentation.explorerfragment

import a77777_888.me.t.domain.model.EmptyLoadResult
import a77777_888.me.t.domain.model.ErrorLoadResult
import a77777_888.me.t.domain.model.LoadResult
import a77777_888.me.t.domain.model.PendingLoadResult
import a77777_888.me.t.domain.repositories.IProductsRepository
import a77777_888.me.t.domain.usecases.ExplorerUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExplorerViewModel : ViewModel() {
    var iProductsRepository: IProductsRepository? = null
        set(value) {
            field = value
            getData()
        }
    private val _loadResultFlow = MutableStateFlow<LoadResult<out Any>>(EmptyLoadResult())
    val loadResultFlow: StateFlow<LoadResult<out Any>> = _loadResultFlow


    fun getData() {
        _loadResultFlow.value = PendingLoadResult()

        viewModelScope.launch {
            _loadResultFlow.value = try {
                if (iProductsRepository != null) {
                    ExplorerUseCase.getData(iProductsRepository!!)
                } else ErrorLoadResult(NullPointerException())
            } catch (e: Exception) {
                ErrorLoadResult(e)
            }

            _loadResultFlow.value = EmptyLoadResult()
        }
    }
}