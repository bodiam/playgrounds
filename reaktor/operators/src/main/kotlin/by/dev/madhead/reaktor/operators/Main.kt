package by.dev.madhead.reaktor.operators

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.util.function.Tuple2
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import java.time.Duration
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    SwingUtilities.invokeLater {
        val frame = JFrame("Mouse Listener").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            extendedState = JFrame.MAXIMIZED_BOTH

            pack()
            isVisible = true

            val mouseEvents = Flux.create<MouseEvent>(
                    { sink ->
                        addMouseMotionListener(object : MouseMotionAdapter() {
                            override fun mouseMoved(mouseMotionEvent: MouseEvent) {
                                sink.next(mouseMotionEvent)
                            }
                        })
                    },
                    FluxSink.OverflowStrategy.DROP
            )
            val ticker = Flux.interval(3.seconds())

            ticker
                    .zipWith(mouseEvents)
                    .map { (tick, event) ->
                        "${tick}: [X: ${event.x}; Y: ${event.y}]"
                    }
                    .subscribe(::println)
            mouseEvents
                    .map { event -> "[X: ${event.x}; Y: ${event.y}]" }
                    .subscribe(::println)
        }
    }
}

fun Number.seconds(): Duration = Duration.ofSeconds(this.toLong())

operator fun <T1, T2> Tuple2<T1, T2>.component1(): T1 = this.t1

operator fun <T1, T2> Tuple2<T1, T2>.component2(): T2 = this.t2
