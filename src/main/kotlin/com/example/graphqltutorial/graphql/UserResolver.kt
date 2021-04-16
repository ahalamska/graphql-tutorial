package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.graphqltutorial.TripRepository
import com.example.graphqltutorial.model.User
import org.springframework.stereotype.Component


@Component
class UserResolver(val tripRepository: TripRepository): GraphQLResolver<User> {


    fun createdTrips(user: User) =
        tripRepository.findTripsByOwner(user.id).map { dto -> dto.getTrip() }

}
