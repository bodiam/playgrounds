package by.dev.madhead.playgrounds.sprink5.kontext

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.BeanDefinitionCustomizer
import org.springframework.beans.factory.getBean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean

open class StarWarsCharacter(val name: String) {
    override fun toString() = name
}

class Luke : StarWarsCharacter("Luke Skywalker")
class Leia : StarWarsCharacter("Leia Organa")
class Han : StarWarsCharacter("Han Solo")
class Chewbakka : StarWarsCharacter("Chewbacca")
class Yoda : StarWarsCharacter("Yoda")
class R2D2 : StarWarsCharacter("R2D2")

fun main(args: Array<String>) {
    val kontext = GenericApplicationContext {
        registerBean(Luke::class)

        registerBean<Leia>()

        registerBean("The Scoundrel", Han::class)

        registerBean<Chewbakka>("Chewie")

        registerBean {
            Yoda()
        }

        registerBean("Artoo") {
            R2D2()
        }

        registerBean<StarWarsCharacter>("another", BeanDefinitionCustomizer {
            it.scope = BeanDefinition.SCOPE_PROTOTYPE
        })
    }

    kontext.refresh()

    println(kontext.getBean(Luke::class))
    println(kontext.getBean<Leia>())

    // Force Kotlin extension by using named parameter
    val han: Han = kontext.getBean("The Scoundrel", requiredType = Han::class)
    println(han)

    println(kontext.getBean<Chewbakka>("Chewie"))
    println(kontext.getBean<Yoda>())
    println(kontext.getBean<R2D2>())

    // Forgot him?
    println(kontext.getBean("another", "Darth Vader"))
}
