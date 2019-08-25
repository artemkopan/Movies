package com.movie.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class AppViewModel : ViewModel() {

    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    inline fun CoroutineScope.main(crossinline block: suspend () -> Unit) {
        launch(Dispatchers.Main) {
            block()
        }
    }

    inline fun CoroutineScope.io(crossinline block: suspend () -> Unit) {
        launch(Dispatchers.IO) {
            block()
        }
    }

    inline fun exceptionHandler(crossinline onError: (Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}