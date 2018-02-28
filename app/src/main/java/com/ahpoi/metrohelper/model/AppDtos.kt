package com.ahpoi.metrohelper.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppUser(val id: String, val firstName: String, val lastName: String) : Parcelable {

    companion object {
        fun fromAuthResponse(response: AuthResponse.AuthResponseSuccess)
                = AppUser(response.id, response.firstName, response.lastName)
    }

}

/**
 * When the AppUser get compiles to java we get something like:
 * public final class AppUser {
 *      private final String id;
 *      private final String firstName:
 *      private final String lastName;
 *
 *      //Constructor
 *      //Getters
 *      //copy(),hashCode(), equals()
 *      //Companion Object:
 *        public static final class Companion {
 *
 *          public final AppUser fromAuthResponse(AuthResponseSuccess response) {
 *              return new AppUser(response.getId(), response.getFirstName(), response.getLastName())
 *          }
 *        }
 * }
 */

data class Journey(val id: String,
                   val fromLocation: String,
                   val toLocation: String,
                   val departureTime: String,
                   val description: String?) {

    companion object {
        fun fromResponse(response: JourneyResponse.Plan) = Journey(response.id, response.from, response.to, response.departureTime, response.description)
    }

}