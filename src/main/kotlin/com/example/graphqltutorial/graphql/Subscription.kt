package com.example.graphqltutorial.graphql

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver
import com.example.graphqltutorial.model.UpdatePlace
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class Subscription(val publisher: TripPublisher): GraphQLSubscriptionResolver {

    fun tripsPlace(tripId: String): Publisher<UpdatePlace> {
        return publisher.getTripPlacePublisher(tripId)
    }
}
