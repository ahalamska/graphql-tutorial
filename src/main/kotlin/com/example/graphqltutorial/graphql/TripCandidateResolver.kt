package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.TripCandidate
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserCandidate
import org.springframework.stereotype.Component
import java.time.OffsetDateTime


@Component
class TripCandidateResolver(val userRepository: UserRepository, val tripRepository: TripRepository): GraphQLResolver<TripCandidate> {


    fun owner(trip: TripCandidate): UserCandidate? {
        tripRepository.findTrip(trip.id)?.let {
            return userRepository.findUser(it.ownerId)?.getUser()
        }
        return null
    }

    fun participants(trip: TripCandidate): List<User> {
        tripRepository.findTrip(trip.id)?.let {
            return userRepository.findUsers(it.participantsId).map{ user -> user.getUser() }
        }
        return emptyList()
    }

    fun name(trip: TripCandidate): String? =
         tripRepository.findTrip(trip.id)?.name

    fun place(trip: TripCandidate): String? =
        tripRepository.findTrip(trip.id)?.place

    fun description(trip: TripCandidate): String? =
        tripRepository.findTrip(trip.id)?.description

    fun maxParticipantsCount(trip: TripCandidate): Int? =
        tripRepository.findTrip(trip.id)?.maxParticipantsCount

    fun price(trip: TripCandidate): Float? =
        tripRepository.findTrip(trip.id)?.price

    fun date(trip: TripCandidate): OffsetDateTime? =
        tripRepository.findTrip(trip.id)?.date

}
