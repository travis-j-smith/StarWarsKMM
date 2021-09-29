package com.iamtravisjsmith.starwarskmm.network

import com.iamtravisjsmith.starwarskmm.entities.Person
import com.iamtravisjsmith.starwarskmm.network.dtos.ApiResponse
import com.iamtravisjsmith.starwarskmm.network.dtos.PersonResponse
import com.iamtravisjsmith.starwarskmm.network.mappers.PersonMapper
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

internal class StarWarsApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    private val personMapper = PersonMapper()

    suspend fun getAllPeople(): List<Person> {
        return httpClient.get<ApiResponse<PersonResponse>>(PEOPLE_ENDPOINT)
            .results.map(personMapper::map)
    }

    suspend fun getPerson(id: Long): Person? {
        return runCatching {
            httpClient.get<PersonResponse>("$PEOPLE_ENDPOINT/$id")
                .let(personMapper::map)
        }.getOrNull()
    }

    companion object {
        private const val PEOPLE_ENDPOINT = "https://swapi.dev/api/people"
    }
}
