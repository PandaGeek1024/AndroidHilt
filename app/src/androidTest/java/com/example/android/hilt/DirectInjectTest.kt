package com.example.android.hilt

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.hilt.data.User
import com.example.android.hilt.di.InMemoryLogger
import com.example.android.hilt.di.LoggingInMemoryModule
import com.example.android.hilt.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DirectInjectTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var user: User

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun happyPath() {
        ActivityScenario.launch(MainActivity::class.java)
        println("dataSource desc: ${user.name}")

        // Check Buttons fragment screen is displayed
        onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Tap on Button 1
        onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // Navigate to Logs screen
        onView(ViewMatchers.withId(R.id.all_logs)).perform(ViewActions.click())

        // Check Logs fragment screen is displayed
//        onView(withText(containsString("Interaction with 'Button 1'")))
//            .check(matches(isDisplayed()))
    }
}