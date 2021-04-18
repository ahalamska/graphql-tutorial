package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserCandidate
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class TripResolver(val userRepository: UserRepository, val tripRepository: TripRepository): GraphQLResolver<Trip> {


    fun name(trip: Trip): String? =
        tripRepository.findTrip(trip.id)?.name

    fun place(trip: Trip): String? =
        tripRepository.findTrip(trip.id)?.place

    fun description(trip: Trip): String? =
        tripRepository.findTrip(trip.id)?.description

    fun maxParticipantsCount(trip: Trip): Int? =
        tripRepository.findTrip(trip.id)?.maxParticipantsCount

    fun pricePln(trip: Trip): BigDecimal? =
        tripRepository.findTrip(trip.id)?.pricePln


    fun owner(trip: Trip): UserCandidate? {
        tripRepository.findTrip(trip.id)?.let {
            return userRepository.findUser(it.ownerId)?.getUser()
        }
        return null
    }

    fun participants(trip: Trip): List<User> {
        tripRepository.findTrip(trip.id)?.let {
            return userRepository.findUsers(it.participantsId).map{ user -> user.getUser() }
        }
        return emptyList()
    }
}
