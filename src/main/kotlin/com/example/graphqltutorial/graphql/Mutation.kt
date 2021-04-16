package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.TripDto
import com.example.graphqltutorial.model.TripPayload
import com.example.graphqltutorial.model.UserDto
import com.example.graphqltutorial.model.UserPayload
import org.springframework.stereotype.Component

@Component
class Mutation(val userRepository: UserRepository, val tripRepository: TripRepository) : GraphQLMutationResolver {

    fun addUser(user: UserDto): UserPayload? =
        UserPayload(userRepository.addUser(user))

    fun addTrip(trip: TripDto): TripPayload? =
        TripPayload(tripRepository.addTrip(trip))

}

