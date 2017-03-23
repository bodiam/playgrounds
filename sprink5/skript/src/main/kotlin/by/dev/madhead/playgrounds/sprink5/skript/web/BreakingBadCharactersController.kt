package by.dev.madhead.playgrounds.sprink5.skript.web

import by.dev.madhead.playgrounds.sprink5.skript.model.BreakingBadCharacter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/characters")
class BreakingBadCharactersController {
    @GetMapping("string")
    fun string(): ModelAndView {
        return ModelAndView(
                "kotlin-template-string",
                mapOf(
                        "jessie" to BreakingBadCharacter("Jessie", "Pinkman", "Alive"),
                        "heisenberg" to BreakingBadCharacter("Walter", "White", "Deceased")
                )
        )
    }

    @GetMapping("kotlinx")
    fun kotlinx(): ModelAndView {
        return ModelAndView(
                "kotlin-template-kotlinx",
                mapOf(
                        "jessie" to BreakingBadCharacter("Jessie", "Pinkman", "Alive"),
                        "heisenberg" to BreakingBadCharacter("Walter", "White", "Deceased")
                )
        )
    }
}
