package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserCandidate
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor


@Component
class TripResolver(val userRepository: UserRepository, val tripRepository: TripRepository, val executor: Executor): GraphQLResolver<Trip> {


    fun name(trip: Trip): CompletableFuture<String?> =
        CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.name
            },
            executor::execute)

    fun place(trip: Trip): CompletableFuture<String?> =
        CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.place
            },
            executor::execute)

    fun description(trip: Trip): CompletableFuture<String?> =
        CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.description
            },
            executor::execute)

    fun maxParticipantsCount(trip: Trip): CompletableFuture<Int?> =
        CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.maxParticipantsCount
            },
            executor::execute)

    fun price(trip: Trip): CompletableFuture<Float?> =
        CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.price
            },
            executor::execute)

    fun date(trip: Trip): CompletableFuture<OffsetDateTime?> =
        CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.date
            },
            executor::execute)

    fun owner(trip: Trip): CompletableFuture<UserCandidate?> {
        return CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.let {
                    userRepository.findUser(it.ownerId)?.getUser()
                }
                null
            },
            executor::execute)

    }

    fun participants(trip: Trip): CompletableFuture<List<User>> {
        return CompletableFuture.supplyAsync(
            {
                tripRepository.findTrip(trip.id)?.let {
                    userRepository.findUsers(it.participantsId).map { user -> user.getUser() }
                }
                emptyList()
            },
            executor::execute)
    }

}
