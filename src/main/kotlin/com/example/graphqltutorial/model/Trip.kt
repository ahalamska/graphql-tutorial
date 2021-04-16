package com.example.graphqltutorial.model


data class Trip (
        val id: String,
        val name: String,
        val place: String,
        val description: String,
        val maxParticipantsCount: Int?,
        val price: Float
)

data class TripDto (
    val id: String,
    val name: String,
    val place: String,
    val description: String,
    val maxParticipantsCount: Int?,
    val price: Float,
    val ownerId: String,
    val participantsId: List<String>
) {
    fun getTrip() =
        Trip(
            id,
            name,
            place,
            description,
            maxParticipantsCount,
            price
        )
}
