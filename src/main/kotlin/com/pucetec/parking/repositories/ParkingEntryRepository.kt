package com.pucetec.parking.repositories

import com.pucetec.parking.models.entities.ParkingEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingEntryRepository : JpaRepository<ParkingEntry, Long> {
    fun findByPlate(plate: String): ParkingEntry?
    fun existsByPlate(plate: String): Boolean
}
