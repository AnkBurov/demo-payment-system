package io.demo.paymentsystem.api.exception

import org.springframework.http.HttpStatus

class ExceptionApiModel(val httpStatus: HttpStatus, val error: String) {
}