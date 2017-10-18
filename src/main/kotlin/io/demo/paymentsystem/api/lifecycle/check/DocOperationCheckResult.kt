package io.demo.paymentsystem.api.lifecycle.check

class DocOperationCheckResult(
        val result: Boolean,
        val errors: MutableList<String> = arrayListOf()
) {
}