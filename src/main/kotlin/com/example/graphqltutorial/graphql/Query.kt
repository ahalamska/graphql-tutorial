package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.graphqltutorial.repository.TripRepository
import com.example.graphqltutorial.repository.UserRepository
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.TripCandidate
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserCandidate
import com.example.graphqltutorial.model.UserNotFound
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class Query(val userRepository: UserRepository, val tripRepository: TripRepository) : GraphQLQueryResolver {
    fun version() = "1.0.0"

    fun user(id: String, dfe: DataFetchingEnvironment): UserCandidate =
        userRepository.findUser(id).let {
            if(it != null) {
                return it.getUser()
            }
            return UserNotFound(id)
        }

    fun users(): List<User> =
        userRepository.findUsers().map {
            it.getUser()
        }

    fun trip(id: String): TripCandidate? =
        tripRepository.findTrip(id).let {
            if(it != null) {
                return it.getTrip()
            }
            return null
        }

    fun trips(): List<Trip> =
        tripRepository.findTrips().map {
            it.getTrip()
        }
}

