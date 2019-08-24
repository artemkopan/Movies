package com.movie.domain.exceptions

class ApiException(override val message: String, val code: Int) : Exception(message)