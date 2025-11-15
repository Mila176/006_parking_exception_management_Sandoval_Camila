package com.pucetec.parking.services

import com.pucetec.parking.exception.*
import com.pucetec.parking.models.entities.ParkingEntry
import com.pucetec.parking.repositories.ParkingEntryRepository
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class ParkingService(
    private val parkingEntryRepository: ParkingEntryRepository
) {
    private val maxCapacity = 20
    private val maxParkingHours = 8L
    private val blacklistedPlates = setOf("AAA-0001", "BBB-0002")
    private val plateRegex = Regex("^[A-Z]{3}-\\d{4}$")

    fun registerEntry(parkingEntry: ParkingEntry): ParkingEntry {
        // Validar formato de placa
        if (!plateRegex.matches(parkingEntry.plate)) {
            throw InvalidPlateFormatException("La placa debe tener el formato AAA-1234")
        }

        // Validar lista negra
        if (blacklistedPlates.contains(parkingEntry.plate)) {
            throw BlacklistedPlateException("La placa ${parkingEntry.plate} está en la lista negra")
        }

        // Validar capacidad máxima
        val currentCount = parkingEntryRepository.count()
        if (currentCount >= maxCapacity) {
            throw ParkingFullException("El parqueadero está lleno (capacidad máxima: $maxCapacity)")
        }

        // Validar si ya está registrado
        if (parkingEntryRepository.existsByPlate(parkingEntry.plate)) {
            throw CarAlreadyParkedException("El auto con placa ${parkingEntry.plate} ya está registrado")
        }

        return parkingEntryRepository.save(parkingEntry)
    }

    fun findByPlate(plate: String): ParkingEntry {
        return parkingEntryRepository.findByPlate(plate)
            ?: throw CarNotFoundException("No se encontró un auto con la placa $plate")
    }

    fun registerExit(plate: String) {
        val parkingEntry = parkingEntryRepository.findByPlate(plate)
            ?: throw CarNotFoundException("No se encontró un auto con la placa $plate")

        // Validar tiempo de permanencia
        val now = LocalDateTime.now()
        val duration = Duration.between(parkingEntry.entryTime, now)
        val hoursParked = duration.toHours()

        if (hoursParked > maxParkingHours) {
            throw ParkingTimeExceededException(
                "El tiempo de permanencia excede las $maxParkingHours horas permitidas (Permanencia: $hoursParked horas)"
            )
        }

        parkingEntryRepository.delete(parkingEntry)
    }
}
