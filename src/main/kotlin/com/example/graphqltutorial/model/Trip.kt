package com.example.graphqltutorial.model

import java.math.BigDecimal

interface TripCandidate {
    val id: String
}

data class TripNotFound(
    override val id: String
) : TripCandidate

data class Trip(
    override val id: String
) : TripCandidate

data class TripDto(
    val id: String,
    val name: String,
    var place: String,
    val description: String,
    val maxParticipantsCount: Int?,
    val pricePln: BigDecimal,
    val ownerId: String,
    val participantsId: List<String>,
) {
    fun getTrip() =
        Trip(
            id
        )
}

data class TripPayload(
    val tripId: String
)

data class UpdatePlacePayload(
    val tripId: String
)

data class UpdatePlace(
    val id: String,
    var place: String,
)
