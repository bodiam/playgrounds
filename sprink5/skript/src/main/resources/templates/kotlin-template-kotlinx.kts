import by.dev.madhead.playgrounds.sprink5.skript.model.BreakingBadCharacter
import kotlinx.html.*
import kotlinx.html.stream.createHTML

createHTML(true).html {
    head {
        title("Kotlin kotlinx.html template")
    }
    body {
        ul {
            for ((nickname, character) in (bindings as Map<String, Any>)) {
                // Spring adds some extra values in bindings
                // Thanks God we have smart casts
                if (!(character is BreakingBadCharacter)) {
                    continue
                }
                li {
                    h3 { +"${character.firstName} ${character.lastName}" }
                    dl {
                        dt {
                            +"Name:"
                        }
                        dd {
                            +(character).firstName
                        }
                        dt {
                            +"Last name:"
                        }
                        dd {
                            +(character).lastName
                        }
                        dt {
                            +"Status:"
                        }
                        dd {
                            +(character).status
                        }
                    }
                }
            }
        }
    }
}
