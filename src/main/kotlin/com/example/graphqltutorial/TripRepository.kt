package com.example.graphqltutorial

import com.example.graphqltutorial.model.TripDto
import org.springframework.stereotype.Service

@Service
class TripRepository {
    
    private var trips: ArrayList<TripDto> = init()

    fun findTrip(id: String): TripDto? {
        return this.trips.find { it.id == id }
    }

    fun findTrips(): List<TripDto> {
        return this.trips
    }

    private final fun init(): ArrayList<TripDto> {
        val trips = ArrayList<TripDto>()
        trips.add(TripDto("1", "Paris", "Paris", "Trip to Paris", 12, 1782.20f, "5", listOf("1","2")))
        trips.add(TripDto("2", "Maledives", "Maledives", "Trip to Maledives", 14,147700.99f, "5", listOf("3","2")))
        trips.add(TripDto("3", "Russia", "Russia", "Trip to Russia", 43, 23000.00f, "5", listOf("4","1")))
        trips.add(TripDto("4", "USA", "USA", "Trip to USA", 44, 10000.00f, "5", listOf("3","4")))
        return trips
    }

    fun findTrips(createdTripsIds: List<String>): List<TripDto> {
        return this.trips.filter { createdTripsIds.contains(it.id) }
    }

    fun findTripsByOwner(ownerId: String): List<TripDto> {
        return this.trips.filter { it.ownerId == ownerId }
    }

}
