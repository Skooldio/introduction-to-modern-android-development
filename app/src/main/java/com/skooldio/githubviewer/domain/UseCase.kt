package com.skooldio.githubviewer.domain

import arrow.core.left
import arrow.core.right
import com.skooldio.githubviewer.common.data.ConnectionError
import com.skooldio.githubviewer.common.data.DataResult


interface UseCase<Input, Output> {
    suspend operator fun invoke(input: Input): DataResult<Output> {
        return handleApiError { execute(input) }
    }

    suspend fun execute(input: Input): Output
}

suspend fun <Output> handleApiError(invoke: suspend () -> Output): DataResult<Output> {
    return try {
        invoke().right()
    } catch (e: Exception) {
        ConnectionError.left()
    }
}
