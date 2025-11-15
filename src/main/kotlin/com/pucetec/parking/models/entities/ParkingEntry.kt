package com.pucetec.parking.models.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "parking_entries")
data class ParkingEntry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    val plate: String,

    @Column(name = "owner_name", nullable = false)
    val ownerName: String,

    @Column(name = "entry_time", nullable = false)
    val entryTime: LocalDateTime = LocalDateTime.now()
)
