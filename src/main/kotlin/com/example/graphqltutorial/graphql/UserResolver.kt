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


@Component
class UserResolver(val tripRepository: TripRepository): GraphQLResolver<User> {

    fun createdTrips(user: User): List<Trip> {
        return tripRepository.findTripsByOwner(user.id).map { dto -> dto.getTrip() }
    }
}
