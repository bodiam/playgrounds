package by.dev.madhead.reaktor.intro.basics

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import toMono
import java.lang.NullPointerException
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

fun main(args: Array<String>) {
    with(Mono.empty<Any>()) {
        subscribe { println("mono1: ${it}") }
    }

    with(Mono.just("foo")) {
        subscribe { println("mono2: ${it}") }
    }

    with(Mono.justOrEmpty<String>(null)) {
        subscribe { println("mono3: ${it}") }
    }

    with(Mono.justOrEmpty<String>("foo")) {
        subscribe { println("mono4: ${it}") }
    }

    with(Mono.justOrEmpty<String>(Optional.empty())) {
        subscribe { println("mono5: ${it}") }
    }

    with(Mono.justOrEmpty<String>(Optional.ofNullable(null))) {
        subscribe { println("mono6: ${it}") }
    }

    with(Mono.justOrEmpty<String>(Optional.ofNullable("foo"))) {
        subscribe { println("mono7: ${it}") }
    }

    with(Mono.justOrEmpty<String>(Optional.of("foo"))) {
        subscribe { println("mono8: ${it}") }
    }

    with("foo".toMono()) {
        subscribe { println("mono9: ${it}") }
    }

    with((Callable<String> { "foo" }).toMono()) {
        subscribe { println("mono10: ${it}") }
    }

    with(CompletableFuture.supplyAsync { 42 }.toMono()) {
        subscribe { println("mono11: ${it}") }
    }

    with(NullPointerException("NPE").toMono<String>()) {
        subscribe({ println("mono12: ${it}") }, { println("mono12!: ${it}") })
    }

    with(Flux.just("foo", "bar", "baz")) {
        subscribe { println("flux1: ${it}") }
    }

    with(Flux.fromIterable(Collections.emptyList<String>())) {
        subscribe { println("flux2: ${it}") }
    }

    with(Flux.fromIterable(arrayListOf("foo", "bar", "baz"))) {
        subscribe { println("flux3: ${it}") }
    }

    with(Flux.fromArray(arrayOf("foo", "bar", "baz"))) {
        subscribe { println("flux4: ${it}") }
    }

    with(Flux.range(1, 10)) {
        subscribe { println("flux5: ${it}") }
    }

    with(Flux.fromArray(arrayOf("foo"))) {
        subscribe({
            println("flux6: ${it}")
        }, {
            // Will not be called
            println("flux6!: ${it}")
        }, {
            println("flux6: completed")
        }, {
            println("flux6: subscription: ${it}")
        })
    }
}
