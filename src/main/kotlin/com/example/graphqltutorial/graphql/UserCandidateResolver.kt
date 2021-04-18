package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Gender
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.UserCandidate
import org.springframework.stereotype.Component


@Component
class UserCandidateResolver(val tripRepository: TripRepository, val userRepository: UserRepository): GraphQLResolver<UserCandidate> {


    fun createdTrips(user: UserCandidate): List<Trip> {
        return tripRepository.findTripsByOwner(user.id).map { dto -> dto.getTrip() }
    }

    fun firstName(user: UserCandidate): String? =
        userRepository.findUser(user.id)?.firstName

    fun surname(user: UserCandidate): String? =
        userRepository.findUser(user.id)?.surname

    fun email(user: UserCandidate): String? =
        userRepository.findUser(user.id)?.email

    fun login(user: UserCandidate): String? =
        userRepository.findUser(user.id)?.login

    fun gender(user: UserCandidate): Gender? =
        userRepository.findUser(user.id)?.gender

    fun age(user: UserCandidate): Int? =
        userRepository.findUser(user.id)?.age
}
