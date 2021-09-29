package com.iamtravisjsmith.starwarskmm.network.mappers

import com.iamtravisjsmith.starwarskmm.entities.Person
import com.iamtravisjsmith.starwarskmm.network.dtos.PersonResponse

internal class PersonMapper {
    fun map(personResponse: PersonResponse): Person {
        val urlParts = personResponse.url.split("/")
        val id = urlParts[urlParts.size - 2].toLong()
        return Person(
            id = id,
            name = personResponse.name,
            birthYear = personResponse.birthYear,
        )
    }
}
