package com.llama.data.di

import com.llama.data.main.GetMiddlePointUseCaseImpl
import com.llama.domain.main.GetMiddlePointUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class GeminiModule {
    @Binds
    abstract fun bindGetMiddlePointUseCase(useCase: GetMiddlePointUseCaseImpl): GetMiddlePointUseCase
}