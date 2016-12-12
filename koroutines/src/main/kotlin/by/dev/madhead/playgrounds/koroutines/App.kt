package by.dev.madhead.playgrounds.koroutines

fun main(args: Array<String>) {
    koroutines1()
//    koroutines2()
}

fun koroutines1() {
    val fibonacci = generate<Int> {
        var prev = 0
        var curr = 1

        while (true) {
            yield(curr)

            val tmp = curr

            curr += prev
            prev = tmp
        }
    }
    val arithmetic = generate<Int> {
        val d = 3
        var curr = 0

        while (true) {
            yield(curr)
            curr += d
        }
    }

    println(fibonacci.take(5).joinToString())
    println(arithmetic.take(5).joinToString())
}

//fun koroutines2() = async {
//    val answer = await(CompletableFuture.supplyAsync { 42 })
//
//    println(answer)
//}
