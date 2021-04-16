package com.example.graphqltutorial.model

import java.time.OffsetDateTime


data class Trip (
        val id: String,
        val name: String,
        val place: String,
        val description: String,
        val maxParticipantsCount: Int?,
        val price: Float,
        val date: OffsetDateTime
)

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
