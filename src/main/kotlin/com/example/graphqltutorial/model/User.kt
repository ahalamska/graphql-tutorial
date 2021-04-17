package com.example.graphqltutorial.model

import java.time.LocalDate

interface UserCandidate {
    val id: String
}

data class User constructor(
        override val id: String
): UserCandidate {}

data class UserNotFound (
        override val id: String
): UserCandidate


data class UserDto (
        val id: String,
        val firstName: String,
        val surname: String,
        val email: String,
        val login: String,
        val gender: Gender?,
        val age: Int?,
        val createdTripsIds: List<String>,
        val birthDate: LocalDate
){
    fun getUser() =
        User(
            id
        )
}

data class UserPayload(
    val userId: String
)

enum class Gender {
    FEMALE, MALE
}
