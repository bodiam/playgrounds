package by.dev.madhead.playgrounds.sprink5.webfluks.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "characters")
data class Character(
        @Id val id: String,
        val name: String
)

@Document(collection = "movies")
data class Movie(
        @Id val id: String,
        val title: String,
        val characters: List<String>
)
