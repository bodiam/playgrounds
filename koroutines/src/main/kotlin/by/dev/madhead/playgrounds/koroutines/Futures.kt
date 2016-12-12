package by.dev.madhead.playgrounds.koroutines

import java.util.concurrent.CompletableFuture

fun <T> async(coroutine lambda: FutureController<T>.() -> Continuation<Unit>): CompletableFuture<T> {
    val controller = FutureController<T>()

    controller.lambda().resume(Unit)

    return controller.future
}

class FutureController<T> {
    val future = CompletableFuture<T>()

    suspend fun await(value: CompletableFuture<T>, continuation: Continuation<T>) {
        value.whenComplete { value, throwable ->
            if (throwable == null) {
                continuation.resume(value)
            } else {
                continuation.resumeWithException(throwable)
            }
        }
    }

    operator fun handleResult(value: T, continuation: Continuation<Nothing>) {
        future.complete(value)
    }

    operator fun handleException(throwable: Throwable, continuation: Continuation<Nothing>) {
        future.completeExceptionally(throwable)
    }
}
