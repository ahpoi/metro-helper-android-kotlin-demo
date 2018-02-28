package com.ahpoi.metrohelper.model

data class AuthRequest(val email: String,
                       val password: String)


sealed class AuthResponse {

    data class AuthResponseSuccess(val id: String,
                                   val firstName: String,
                                   val lastName: String,
                                   val email: String) : AuthResponse()

    data class AuthResponseFailure(val errorMessage: String) : AuthResponse()

}

sealed class JourneyResponse {

    data class Plan(val id: String,
                    val createdOn: String,
                    val updatedOn: String,
                    val from: String,
                    val to: String,
                    val departureTime: String,
                    val description: String?  )

    data class JourneyResponseSuccess(val journeys: List<Plan> = listOf()) : JourneyResponse()

    data class JourneyResponseFailure(val errorMessage: String) : JourneyResponse()

}