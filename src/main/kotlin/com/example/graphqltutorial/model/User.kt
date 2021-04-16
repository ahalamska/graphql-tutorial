package com.example.graphqltutorial.model

data class User (
        val id: String,
        val firstName: String,
        val surname: String,
        val email: String,
        val login: String,
        val gender: Gender?,
        val age: Int?
)

data class UserDto (
        val id: String,
        val firstName: String,
        val surname: String,
        val email: String,
        val login: String,
        val gender: Gender?,
        val age: Int?,
        val createdTripsIds: List<String>
){
    fun getUser() =
        User(
            id,
            firstName,
            surname,
            email,
            login,
            gender,
            age
        )
}

enum class Gender {
    FEMALE, MALE
}
