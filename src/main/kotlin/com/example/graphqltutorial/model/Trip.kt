package com.example.graphqltutorial.model

import java.math.BigDecimal


data class Trip (
        val id: String,
        val name: String,
        val place: String,
        val description: String,
        val maxParticipantsCount: Int?,
        val pricePln: BigDecimal
)

data class TripDto (
    val id: String,
    val name: String,
    val place: String,
    val description: String,
    val maxParticipantsCount: Int?,
    val pricePln: BigDecimal,
    val ownerId: String,
    val participantsId: List<String>,
) {
    fun getTrip() =
        Trip(
            id,
            name,
            place,
            description,
            maxParticipantsCount,
            pricePln
        )
}

data class TripPayload(
    val tripId: String
)
