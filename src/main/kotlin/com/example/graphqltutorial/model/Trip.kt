package com.example.graphqltutorial.model

import java.time.OffsetDateTime

interface TripCandidate {
    val id: String
}

data class TripNotFound(
    override val id: String
) : TripCandidate

data class Trip(
    override val id: String,
    val name: String,
    val place: String,
    val description: String,
    val maxParticipantsCount: Int?,
    val price: Float,
    val date: OffsetDateTime
) : TripCandidate

data class TripDto(
    val id: String,
    val name: String,
    val place: String,
    val description: String,
    val maxParticipantsCount: Int?,
    val price: Float,
    val ownerId: String,
    val participantsId: List<String>,
    val date: OffsetDateTime
) {
    fun getTrip() =
        Trip(
            id,
            name,
            place,
            description,
            maxParticipantsCount,
            price,
            date
        )
}

data class TripPayload(
    val tripId: String
)
