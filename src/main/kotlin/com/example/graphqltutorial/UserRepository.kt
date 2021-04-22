package com.example.graphqltutorial

import com.example.graphqltutorial.model.Gender
import com.example.graphqltutorial.model.UserDto
import org.springframework.stereotype.Service

@Service
class UserRepository {

    private var users: ArrayList<UserDto> = init() as ArrayList<UserDto>

    fun findUser(id: String): UserDto? {
        return users.find { it.id == id }
    }

    fun findUsers(): List<UserDto> {
        return this.users
    }
    fun findUsers(ids: List<String>, limit: Int): List<UserDto> {
        return this.users.filter { ids.contains(it.id) }.take(limit)
    }

    private final fun init(): List<UserDto> {
        val users = ArrayList<UserDto>()
        users.add(UserDto("1", "Adam", "Kowalski", "adam.kowalski@gmail.com", "akowalski", Gender.MALE, 13, emptyList()))
        users.add(UserDto("2", "Marek", "Kowalski", "marek.kowalski@gmail.com", "mkowalski", Gender.MALE, 45, emptyList()))
        users.add(UserDto("3", "Renata", "Kowalska", "renata.kowalska@gmail.com", "rkowalska", Gender.FEMALE, 43, emptyList()))
        users.add(UserDto("4", "Katarzyna", "Kowalska", "kasia.kowalska@gmail.com", "kkowalska", Gender.FEMALE, 10, emptyList()))
        users.add(UserDto("5", "Adam", "Nowak", "adam.nowak@gmail.com", "anowak", Gender.MALE, 36, listOf("1", "2", "3", "4")))
        return users
    }

    fun addUser(user: UserDto): String {
        val res = users.add(user)
        if (res) {
            return user.id
        }
        else throw UnsupportedOperationException()
    }

}
