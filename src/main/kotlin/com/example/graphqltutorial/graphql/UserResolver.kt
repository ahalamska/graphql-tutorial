package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Gender
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.User
import org.springframework.stereotype.Component


@Component
class UserResolver(val tripRepository: TripRepository, val userRepository: UserRepository): GraphQLResolver<User> {

    fun firstName(user: User): String? =
        userRepository.findUser(user.id)?.firstName

    fun surname(user: User): String? =
        userRepository.findUser(user.id)?.surname

    fun email(user: User): String? =
        userRepository.findUser(user.id)?.email

    fun login(user: User): String? =
        userRepository.findUser(user.id)?.login

    fun gender(user: User): Gender? =
        userRepository.findUser(user.id)?.gender

    fun age(user: User): Int? =
        userRepository.findUser(user.id)?.age

    fun createdTrips(user: User): List<Trip> {
        return tripRepository.findTripsByOwner(user.id).map { dto -> dto.getTrip() }
    }
}
