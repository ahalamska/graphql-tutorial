package com.example.graphqltutorial.model


interface UserCandidate {
    val id: String
}

data class User constructor(
        override val id: String
): UserCandidate {}

data class UserNotFound (
        override val id: String
): UserCandidate


data class UserDto(
    val id: String,
    val firstName: String,
    val surname: String,
    val email: String,
    val login: String,
    val gender: Gender?,
    var age: Int?,
    val createdTripsIds: List<String>,
) {
    fun getUser() =
        User(
            id
        )
}

data class UserPayload(
    val userId: String
)

data class UpdateAgePayload(
    val userId: String
)

enum class Gender {
    FEMALE, MALE
}

data class UpdateAge(
    val id: String,
    var age: Int,
)
