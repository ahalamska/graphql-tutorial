package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver
import com.example.graphqltutorial.model.UpdateAge
import com.example.graphqltutorial.model.UpdatePlace
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class Subscription(val tripPublisher: TripPublisher, val userPublisher: UserPublisher): GraphQLSubscriptionResolver {

    fun tripsPlace(tripId: String): Publisher<UpdatePlace> {
        return tripPublisher.getTripPlacePublisher(tripId)
    }

    fun userAge(userId: String): Publisher<UpdateAge> {
        return userPublisher.getUserAgePublisher(userId)
    }
}
