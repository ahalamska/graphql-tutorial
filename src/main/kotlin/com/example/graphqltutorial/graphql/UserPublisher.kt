package com.example.graphqltutorial.graphql

import com.example.graphqltutorial.model.UpdateAge
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Sinks


@Component
class UserPublisher {

    private val sink: Sinks.Many<UpdateAge> = Sinks.many().multicast().directAllOrNothing()

    fun getUserAgePublisher(userId: String): Publisher<UpdateAge> {
        return sink.asFlux()
            .filter {userId == it.id}
            .also { processor -> processor.map {println("Publishing user age changed: [$it]")} }
    }

    fun publish(update: UpdateAge) {
        sink.emitNext(update, Sinks.EmitFailureHandler.FAIL_FAST)
    }

}
