package com.pucetec.parking.controllers

import com.pucetec.parking.models.entities.ParkingEntry
import com.pucetec.parking.services.ParkingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/parking/entries")
class ParkingController(
    private val parkingService: ParkingService
) {

    @PostMapping
    fun registerEntry(@RequestBody parkingEntry: ParkingEntry): ResponseEntity<ParkingEntry> {
        val savedEntry = parkingService.registerEntry(parkingEntry)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry)
    }

    @GetMapping("/{plate}")
    fun getEntryByPlate(@PathVariable plate: String): ResponseEntity<ParkingEntry> {
        val entry = parkingService.findByPlate(plate)
        return ResponseEntity.ok(entry)
    }

    @DeleteMapping("/{plate}")
    fun registerExit(@PathVariable plate: String): ResponseEntity<Map<String, String>> {
        parkingService.registerExit(plate)
        return ResponseEntity.ok(mapOf("message" to "Auto con placa $plate salió del parqueadero"))
    }
}
