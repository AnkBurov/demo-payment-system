package io.demo.paymentsystem.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/health")
class HealthCheckController {

    @GetMapping
    fun checkHealth() = "ok"
}