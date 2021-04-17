package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Gender
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.TripCandidate
import com.example.graphqltutorial.model.User
import com.example.graphqltutorial.model.UserCandidate
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


@Component
class UserResolver(val tripRepository: TripRepository, val userRepository: UserRepository, val executor: Executor) : GraphQLResolver<User> {


    fun createdTrips(user: User): CompletableFuture<List<Trip>> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                tripRepository.findTripsByOwner(user.id).map { dto -> dto.getTrip() }
            },
            executor::execute)
    }

    fun firstName(user: User): CompletableFuture<String?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.firstName
            },
            executor::execute)
    }

    fun surname(user: User): CompletableFuture<String?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.surname
            },
            executor::execute)
    }

    fun email(user: User): CompletableFuture<String?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.email
            },
            executor::execute)
    }

    fun login(user: User): CompletableFuture<String?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.login
            },
            executor::execute)
    }

    fun gender(user: User): CompletableFuture<Gender?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.gender
            },
            executor::execute)
    }

    fun age(user: User): CompletableFuture<Int?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.age
            },
            executor::execute)
    }

    fun birthDate(user: User): CompletableFuture<LocalDate?> {
        return CompletableFuture.supplyAsync(
            {
                TimeUnit.MILLISECONDS.sleep(100)
                userRepository.findUser(user.id)?.birthDate
            },
            executor::execute)
    }

}
