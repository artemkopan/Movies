package com.movie.domain.common

interface UseCaseAsync<Result> {
    suspend fun execute(): Result
}

interface UseCaseAsyncParams<Params, Result> {
    suspend fun execute(params: Params): Result
}

interface UseCaseAsyncBiParams<Params1, Params2, Result> {
    suspend fun execute(params1: Params1, params2: Params2): Result
}

interface UseCaseAsyncParams3<Params1, Params2, Params3, Result> {
    suspend fun execute(params1: Params1, params2: Params2, params3: Params3): Result
}