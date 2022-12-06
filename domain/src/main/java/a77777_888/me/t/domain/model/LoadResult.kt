package a77777_888.me.t.domain.model

sealed class LoadResult<T>

class EmptyLoadResult<T>: LoadResult<T>()

class PendingLoadResult<T> : LoadResult<T>()

class SuccessLoadResult<T>(val value: T) : LoadResult<T>()

class ErrorLoadResult<T>(val exception: Exception) : LoadResult<T>()