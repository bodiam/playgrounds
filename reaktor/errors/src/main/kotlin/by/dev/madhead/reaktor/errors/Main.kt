package by.dev.madhead.reaktor.errors

import reactor.core.publisher.Flux


fun main(args: Array<String>) {
    val s = Flux.range(1, 10)
            .map(::doSomethingDangerous)
            .subscribe(
                    { value -> println("RECEIVED " + value) },
                    { error -> System.err.println("CAUGHT " + error) }
            )
}

fun doSomethingDangerous(i: Int): Int {
    if (i % 3 == 0) throw IllegalArgumentException("Cannot handle that!")
    return i
}