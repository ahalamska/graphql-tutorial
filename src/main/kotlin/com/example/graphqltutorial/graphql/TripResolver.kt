package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserCandidate
import org.springframework.stereotype.Component


@Component
class TripResolver(val userRepository: UserRepository, val tripRepository: TripRepository): GraphQLResolver<Trip> {


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
