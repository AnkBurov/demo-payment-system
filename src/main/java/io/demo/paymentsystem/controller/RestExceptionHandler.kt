package io.demo.paymentsystem.controller

import io.demo.paymentsystem.api.exception.ExceptionApiModel
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import javax.persistence.OptimisticLockException

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleUncaughtException(ex: Exception): ResponseEntity<ExceptionApiModel> {
        LOG.error("Handle uncaught exception", ex)
        val errorPair = when (ex) {
            is IllegalArgumentException -> HttpStatus.BAD_REQUEST to if (ex.message.isNullOrBlank()) DEFAULT_ERROR else ex.message!!
            is OptimisticLockException -> HttpStatus.CONFLICT to CONCURRENT_ACCESS_CONFLICT
            else -> HttpStatus.INTERNAL_SERVER_ERROR to DEFAULT_ERROR
        }
        val exceptionApiModel = ExceptionApiModel(errorPair)
        return ResponseEntity<ExceptionApiModel>(exceptionApiModel, exceptionApiModel.httpStatus)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
        private val CONCURRENT_ACCESS_CONFLICT = "The record you are working on has been modified by another user. " +
                "Update the page and try again"
        private val DEFAULT_ERROR = "Internal error"
    }
}