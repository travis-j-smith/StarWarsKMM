package com.iamtravisjsmith.starwarskmm.persistence

import com.iamtravisjsmith.starwarskmm.entities.Person
import com.iamtravisjsmith.starwarskmm.repositories.mappers.PersonMapper
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

internal class StarWarsDatabase(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries
    private val personMapper = PersonMapper()

    fun getAllPeople(): Flow<List<Person>> {
        return dbQuery.selectAllPeople(personMapper::map).asFlow().mapToList()
    }

    fun getPersonById(id: Long): Flow<Person?> {
        return dbQuery.selectPersonById(id, personMapper::map).asFlow().mapToOneOrNull()
    }

    fun insertPeople(people: List<Person>) {
        people.forEach(this::insertPerson)
    }

    fun insertPerson(person: Person) {
        dbQuery.insertPerson(
            id = person.id,
            name = person.name,
            birthYear = person.birthYear
        )
    }
}
