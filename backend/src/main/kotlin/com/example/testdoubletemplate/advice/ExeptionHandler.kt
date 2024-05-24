package com.example.testdoubletemplate.advice

import com.example.testdoubletemplate.model.response.ResponceError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ExeptionHandler {
    @ExceptionHandler(value = [UserNotFoundException::class])
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handlerUserNotFoundException(exception: UserNotFoundException,request:WebRequest):ResponceError {
        return ResponceError(
            message = exception.message ?: "User Not Found"
        )
    }

    @ExceptionHandler(value = [InvalidDateException::class])
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    fun handleInvalidDateException(exception: InvalidDateException, request: WebRequest): ResponceError {
        return ResponceError(
            message = exception.message ?: "Invalid date input"
        )
    }
}

class UserNotFoundException(message:String):RuntimeException(message)

class InvalidDateException(message: String? =null) : RuntimeException(message)
