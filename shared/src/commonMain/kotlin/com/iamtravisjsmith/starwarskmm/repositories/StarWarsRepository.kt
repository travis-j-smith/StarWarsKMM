package com.iamtravisjsmith.starwarskmm.repositories

import com.iamtravisjsmith.starwarskmm.entities.Person
import com.iamtravisjsmith.starwarskmm.network.StarWarsApi
import com.iamtravisjsmith.starwarskmm.persistence.DatabaseDriverFactory
import com.iamtravisjsmith.starwarskmm.persistence.StarWarsDatabase
import kotlinx.coroutines.flow.first

class StarWarsRepository(databaseDriverFactory: DatabaseDriverFactory) {
    private val starWarsApi = StarWarsApi()
    private val starWarsDatabase = StarWarsDatabase(databaseDriverFactory)

    suspend fun getPeople(): List<Person> {
        val cachedPeople = starWarsDatabase.getAllPeople().first()
        return if (cachedPeople.isNotEmpty()) {
            cachedPeople
        } else {
            starWarsApi.getAllPeople()
                .also { people ->
                    starWarsDatabase.insertPeople(people)
                }
        }
    }

    suspend fun getPerson(id: Long): Person? {
        val cachedPerson = starWarsDatabase.getPersonById(id).first()
        return cachedPerson ?: starWarsApi.getPerson(id).also { person ->
            person?.let(starWarsDatabase::insertPerson)
        }
    }
}
