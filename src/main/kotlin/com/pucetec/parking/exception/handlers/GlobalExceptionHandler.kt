package com.pucetec.parking.exception.handlers

import com.pucetec.parking.exception.*
import com.pucetec.parking.models.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ParkingFullException::class)
    fun handleParkingFullException(ex: ParkingFullException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(CarAlreadyParkedException::class)
    fun handleCarAlreadyParkedException(ex: CarAlreadyParkedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(InvalidPlateFormatException::class)
    fun handleInvalidPlateFormatException(ex: InvalidPlateFormatException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(BlacklistedPlateException::class)
    fun handleBlacklistedPlateException(ex: BlacklistedPlateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.FORBIDDEN
        )
    }

    @ExceptionHandler(CarNotFoundException::class)
    fun handleCarNotFoundException(ex: CarNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(ParkingTimeExceededException::class)
    fun handleParkingTimeExceededException(ex: ParkingTimeExceededException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.BAD_REQUEST
        )
    }
}
