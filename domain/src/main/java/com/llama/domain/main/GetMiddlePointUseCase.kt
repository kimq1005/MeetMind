package com.llama.domain.main

interface GetMiddlePointUseCase {
    suspend operator fun invoke(
        middlePointRequest: String,
        placeType: String
    ): Result<String>
}