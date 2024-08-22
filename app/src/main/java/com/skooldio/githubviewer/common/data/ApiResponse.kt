package com.skooldio.githubviewer.common.data

import arrow.core.Either

typealias DataResult<Success> = Either<GenericError, Success>

interface GenericError

object ConnectionError : GenericError

data class ServerError(val message: String) : GenericError
