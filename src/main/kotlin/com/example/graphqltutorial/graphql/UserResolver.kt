package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.UserRepository
import com.example.graphqltutorial.model.Gender
import com.example.graphqltutorial.model.Trip
import com.example.graphqltutorial.model.User
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit


@Component
class UserResolver(val tripRepository: TripRepository, val userRepository: UserRepository): GraphQLResolver<User> {


    fun createdTrips(user: User): List<Trip> {
        TimeUnit.MILLISECONDS.sleep(100)
        return tripRepository.findTripsByOwner(user.id).map { dto -> dto.getTrip() }
    }

    fun firstName(user: User): String? {
        TimeUnit.MILLISECONDS.sleep(100)
        return userRepository.findUser(user.id)?.firstName
    }

    fun surname(user: User): String? {
        TimeUnit.MILLISECONDS.sleep(100)
        return userRepository.findUser(user.id)?.surname
    }

    fun email(user: User): String? {
        TimeUnit.MILLISECONDS.sleep(100)
        return userRepository.findUser(user.id)?.email
    }

    fun login(user: User): String? {
        TimeUnit.MILLISECONDS.sleep(100)
        return userRepository.findUser(user.id)?.login
    }

    fun gender(user: User): Gender? {
        TimeUnit.MILLISECONDS.sleep(100)
        return userRepository.findUser(user.id)?.gender
    }

    fun age(user: User): Int? {
        TimeUnit.MILLISECONDS.sleep(100)
        return userRepository.findUser(user.id)?.age
    }

}
