package com.example.graphqltutorial.graphql

import com.example.graphqltutorial.model.UpdatePlace
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Sinks


@Component
class TripPublisher {

    private val sink: Sinks.Many<UpdatePlace> = Sinks.many().multicast().directAllOrNothing()

    fun getTripPlacePublisher(tripId: String): Publisher<UpdatePlace> {
        return sink.asFlux()
            .filter {tripId == it.id}
            .also { processor -> processor.map {println("Publishing trips place changed: [$it]")} }
    }

    fun publish(update: UpdatePlace) {
        sink.emitNext(update, Sinks.EmitFailureHandler.FAIL_FAST)
    }

}
