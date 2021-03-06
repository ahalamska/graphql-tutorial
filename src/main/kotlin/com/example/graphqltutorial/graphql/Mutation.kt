package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.graphqltutorial.repository.TripRepository
import com.example.graphqltutorial.repository.UserRepository
import com.example.graphqltutorial.model.TripDto
import com.example.graphqltutorial.model.TripPayload
import com.example.graphqltutorial.model.UpdateAge
import com.example.graphqltutorial.model.UpdateAgePayload
import com.example.graphqltutorial.model.UpdatePlace
import com.example.graphqltutorial.model.UpdatePlacePayload
import com.example.graphqltutorial.model.UserDto
import com.example.graphqltutorial.model.UserPayload
import org.springframework.stereotype.Component

@Component
class Mutation(
    val userRepository: UserRepository,
    val tripRepository: TripRepository,
    val tripPublisher: TripPublisher,
    val userPublisher: UserPublisher
) : GraphQLMutationResolver {

    fun addUser(user: UserDto): UserPayload? =
        UserPayload(userRepository.addUser(user))

    fun addTrip(trip: TripDto): TripPayload? =
        TripPayload(tripRepository.addTrip(trip))

    fun updatePlace(update: UpdatePlace): UpdatePlacePayload? =
        UpdatePlacePayload(tripRepository.updatePlace(update.id, update.place))
            .also {
                tripPublisher.publish(update)
            }

    fun updateAge(update: UpdateAge): UpdateAgePayload? =
        UpdateAgePayload(userRepository.updateAge(update.id, update.age))
            .also {
                userPublisher.publish(update)
            }
}

