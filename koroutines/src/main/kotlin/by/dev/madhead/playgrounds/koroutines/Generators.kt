package by.dev.madhead.playgrounds.koroutines

fun <T> generate(coroutine lambda: GeneratorIterator<T>.() -> Continuation<Unit>): Sequence<T> = object : Sequence<T> {
    override fun iterator(): Iterator<T> {
        val iterator = GeneratorIterator<T>()

        iterator.setContinuation(iterator.lambda())

        return iterator
    }
}

class GeneratorIterator<T> : AbstractIterator<T>() {
    private lateinit var continuation: Continuation<Unit>

    override fun computeNext() {
        continuation.resume(Unit)
    }

    fun setContinuation(continuation: Continuation<Unit>) {
        this.continuation = continuation
    }

    suspend fun yield(value: T, continuation: Continuation<Unit>) {
        setNext(value)
        setContinuation(continuation)
    }
}
