package by.dev.madhead.reaktor.intro.create

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    SwingUtilities.invokeLater {
        val frame = JFrame("Mouse Listener").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            extendedState = JFrame.MAXIMIZED_BOTH

            pack()
            isVisible = true

            val flux = Flux.create<MouseEvent>(
                    { sink ->
                        addMouseMotionListener(object : MouseMotionAdapter() {
                            override fun mouseMoved(mouseMotionEvent: MouseEvent) {
                                sink.next(mouseMotionEvent)
                            }
                        })
                    },
                    FluxSink.OverflowStrategy.LATEST
            )

            flux
                    .map { "[X: ${it.x}; Y: ${it.y}]" }
                    .subscribe(::println)
        }
    }
}
