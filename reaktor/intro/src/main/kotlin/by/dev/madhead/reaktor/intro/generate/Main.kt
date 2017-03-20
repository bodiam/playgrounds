package by.dev.madhead.reaktor.intro.generate

import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink

fun main(args: Array<String>) {
    Flux.generate(
            { arrayOf(0, 1) }, // Not thread-safe
            { state: Array<Int>, sink: SynchronousSink<String> ->
                sink.next("Next Fibonnaci number: ${state[0]}")

                val tmp = state[1]

                state [1] += state[0]
                state[0] = tmp
                state
            }
    )
            .take(10)
            .subscribe(::println)

    println()

    Flux.generate(
            { arrayOf(0, 1) }, // Not thread-safe
            { state: Array<Int>, sink: SynchronousSink<String> ->
                sink.next("Next Fibonnaci number: ${state[0]}")

                val tmp = state[1]

                state [1] += state[0]
                state[0] = tmp
                state
            },
            { state -> println("Finalizing state: ${state}") }
    )
            .take(10)
            .subscribe(::println)
}
