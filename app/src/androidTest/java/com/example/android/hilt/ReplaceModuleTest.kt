/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hilt

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.android.hilt.data.Log
import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.di.InMemoryLogger
import com.example.android.hilt.di.LoggingDatabaseModule
import com.example.android.hilt.di.LoggingInMemoryModule
import com.example.android.hilt.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class FakeDataSource @Inject constructor() : LoggerDataSource {

    init {
        println()
    }

    override val description: String
        get() = "This is Fake DataSource"

    init {
        println("")
    }

    override fun addLog(msg: String) {
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
    }

    override fun removeLogs() {
    }
}

@RunWith(AndroidJUnit4::class)
@UninstallModules(LoggingInMemoryModule::class)
@HiltAndroidTest
class ReplaceModuleTest {

    @Module
    @InstallIn(ActivityComponent::class)
    abstract class TestModule {

        @InMemoryLogger
        @ActivityScoped
        @Binds
        abstract fun bindAnalyticsService(
            fakeDataSource: FakeDataSource
        ): LoggerDataSource
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun happyPath() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        onView(withId(R.id.textView)).check(matches(isDisplayed()))

        // Tap on Button 1
        onView(withId(R.id.button1)).perform(click())

        // Navigate to Logs screen
        onView(withId(R.id.all_logs)).perform(click())

        // Check Logs fragment screen is displayed
//        onView(withText(containsString("Interaction with 'Button 1'")))
//            .check(matches(isDisplayed()))
    }
}


