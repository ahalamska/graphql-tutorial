package com.example.graphqltutorial.model

import java.time.LocalDate

data class User (
        val id: String,
        val firstName: String,
        val surname: String,
        val email: String,
        val login: String,
        val gender: Gender?,
        val age: Int?,
        val birthDate: LocalDate,
)

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
            id,
            firstName,
            surname,
            email,
            login,
            gender,
            age,
            birthDate
        )
}

data class UserPayload(
    val userId: String
)

enum class Gender {
    FEMALE, MALE
}
