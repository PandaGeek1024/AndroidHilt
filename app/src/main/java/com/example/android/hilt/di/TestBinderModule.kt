package com.example.android.hilt.di

import com.example.android.hilt.data.RealBinder
import com.example.android.hilt.data.TestBinder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class TestBinderModule {
    @Singleton
    @Binds
    abstract fun bindTestBinder(impl: RealBinder): TestBinder
}