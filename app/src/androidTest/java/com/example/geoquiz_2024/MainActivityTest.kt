package com.example.geoquiz_2024

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @BeforeEach
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @AfterEach
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun affiche1eQuestionLancement(){
        onView(withId(R.id.tvQuestion))
            .check(matches(withText(R.string.question_1)))
    }
}
