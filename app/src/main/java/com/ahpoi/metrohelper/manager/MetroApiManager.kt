package com.ahpoi.metrohelper.manager

import com.ahpoi.metrohelper.model.AuthRequest
import com.ahpoi.metrohelper.model.AuthResponse
import com.ahpoi.metrohelper.model.AuthResponse.*
import com.ahpoi.metrohelper.model.JourneyResponse
import org.androidannotations.annotations.EBean
import java.util.*

private const val SIMULATE_AUTH_FAILURE = "fail"

@EBean(scope = EBean.Scope.Singleton)
class ApiManager {

    fun auth(email: String, password: String): AuthResponse {
        val request = AuthRequest(email = email, password = password)
        val response = this.makeRequest(request) //Simulate network request
        return when (password) {
            SIMULATE_AUTH_FAILURE -> AuthResponseFailure("Invalid credentials") //Simulate error
            else -> AuthResponseSuccess(UUID.randomUUID().toString(), "John", "Doe", email)
        }
    }

    fun getJourneys(userId: String): JourneyResponse.JourneyResponseSuccess {
        val plan1 = JourneyResponse.Plan(
                id = UUID.randomUUID().toString(),
                createdOn = "12/23/2000",
                updatedOn = "12/23/2003",
                departureTime = "18:00",
                description = null,
                from = "Parliament Station",
                to = "Home")
        val plan2 = plan1.copy(description = "Take 865 Bus at Berwick")
        val plans = listOf(plan1, plan2) //Immutable collection
        return JourneyResponse.JourneyResponseSuccess(journeys = plans)
    }

    private fun makeRequest(any: Any) {
        //Simulate network request
    }

}


