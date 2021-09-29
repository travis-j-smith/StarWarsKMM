package com.iamtravisjsmith.starwarskmm.repositories.mappers

import com.iamtravisjsmith.starwarskmm.entities.Person

class PersonMapper {

    fun map(id: Long, name: String, birthYear: String): Person {
        return Person(
            id = id,
            name = name,
            birthYear = birthYear,
        )
    }
}
