package com.fabioaacarneiro.contactlistk.exceptions.contactExceptions

import com.fabioaacarneiro.contactlistk.exceptions.MessageException
import com.fabioaacarneiro.contactlistk.exceptions.MessageExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice("com.fabioaacarneiro.contactlistk.controllers")
class ContactControllerAdvice {
    @ExceptionHandler(ContactNotFoundException::class)
    fun contactNotFond(): ResponseEntity<MessageExceptionHandler> {
        val eMessage = MessageException(
            "Contact is not found",
            "One or more errors occurred looking for the contact."
        )

        val eList = listOf(eMessage)
        val error = MessageExceptionHandler(
            timestamp = java.util.Date(),
            status = HttpStatus.NOT_FOUND.value(),
            errors = eList
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ContactCannotBeDeletedException::class)
    fun contactCannotBeDeleted(): ResponseEntity<MessageExceptionHandler> {
        val eMessage = MessageException(
            "Contact cannot be deleted",
            "One or more errors occurred on trying delete the contact."
        )

        val eList = listOf(eMessage)
        val error = MessageExceptionHandler(
            timestamp =  Date(),
            status = HttpStatus.NOT_FOUND.value(),
            errors = eList
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ContactCannotInsertedException::class)
    fun contactCannotInserted(e: MethodArgumentNotValidException): ResponseEntity<MessageExceptionHandler> {

        val fErrors = e.fieldErrors

        val eList = fErrors.map {
            fError -> MessageException(
                fError.field,
                "One or more errors occurred on trying insert the contact."
            )
        }

        val error = MessageExceptionHandler(
            timestamp =  Date(),
            status = HttpStatus.NOT_FOUND.value(),
            errors = eList
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}