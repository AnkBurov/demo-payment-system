package io.demo.paymentsystem.controller

import io.demo.paymentsystem.api.exception.ExceptionApiModel
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleUncaughtException(ex: Exception): ResponseEntity<ExceptionApiModel> {
        LOG.error("Handle uncaught exception", ex)
        val exceptionApiModel = if (ex is IllegalArgumentException) {
            ExceptionApiModel(HttpStatus.BAD_REQUEST, if (ex.message.isNullOrBlank()) DEFAULT_ERROR else ex.message!!)
        } else {
            ExceptionApiModel(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_ERROR)
        }
        return ResponseEntity<ExceptionApiModel>(exceptionApiModel, exceptionApiModel.httpStatus)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
        private val DEFAULT_ERROR = "Internal error"
    }
}