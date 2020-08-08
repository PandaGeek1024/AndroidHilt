package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.User
import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    @ActivityScoped
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator

}

//@InstallIn(ActivityComponent::class)
//@Module
//object NavigationModule {
//    @Provides
//    fun bindNavigator(impl: AppNavigatorImpl): AppNavigator {
//        return impl
//    }
//}