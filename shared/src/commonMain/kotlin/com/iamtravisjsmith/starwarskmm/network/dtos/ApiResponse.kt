package com.iamtravisjsmith.starwarskmm.network.dtos

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiResponse<T : Any>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>
)
