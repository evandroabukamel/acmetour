package com.acme.tour.advice

import com.acme.tour.exception.PromotionNotFoundException
import com.acme.tour.model.ErrorResponse
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(JsonParseException::class)
    fun jsonParseExceptionHandler(
        req: HttpServletRequest,
        res: HttpServletResponse,
        exception: Exception
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                JsonParseException::class.simpleName.toString(),
                exception.message ?: "Invalid JSON format."
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(PromotionNotFoundException::class)
    fun promotionNotFoundExceptionHandler(
        req: HttpServletRequest,
        res: HttpServletResponse,
        exception: Exception
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                PromotionNotFoundException::class.simpleName.toString(),
                exception.message ?: "Promotion(s) not found."
            ),
            HttpStatus.NOT_FOUND
        )
    }
}
