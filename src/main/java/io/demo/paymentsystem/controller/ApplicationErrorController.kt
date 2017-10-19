package io.demo.paymentsystem.controller

import io.demo.paymentsystem.api.exception.ExceptionApiModel
import io.demo.paymentsystem.controller.ApplicationErrorController.Companion.ERROR_PATH
import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping


@RestController
@RequestMapping(ERROR_PATH)
class ApplicationErrorController : ErrorController {
    override fun getErrorPath(): String {
        return ERROR_PATH
    }

    @GetMapping
    fun error(): ResponseEntity<ExceptionApiModel> = ResponseEntity(ExceptionApiModel(HttpStatus.NOT_FOUND, "Requested page is not found"), HttpStatus.NOT_FOUND)

    companion object {
        const val ERROR_PATH = "/error"
    }
}