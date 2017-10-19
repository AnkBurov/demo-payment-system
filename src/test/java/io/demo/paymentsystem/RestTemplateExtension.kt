package io.demo.paymentsystem

import org.junit.Assert
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> ResponseEntity<T?>.ok(message: String? = null): ResponseEntity<T?> = this.checkStatus(HttpStatus.OK, message)

fun <T> ResponseEntity<T?>.badRequest(message: String? = null): ResponseEntity<T?> = this.checkStatus(HttpStatus.BAD_REQUEST, message)

private fun <T> ResponseEntity<T?>.checkStatus(statusCode: HttpStatus, message: String? = null): ResponseEntity<T?> {
    Assert.assertEquals(message, statusCode, this.statusCode)
    return this
}

fun <T> ResponseEntity<T?>.bodyNotNull(message: String? = null) = this.body ?: throw AssertionError(message)