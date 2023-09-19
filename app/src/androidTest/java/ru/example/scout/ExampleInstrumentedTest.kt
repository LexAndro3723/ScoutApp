package ru.example.scout

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import ru.example.scout.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkAllComponentIsVisible_Success() {

        writeEditTextInField(R.id.login, "7XXXXXXXXXX")
        writeEditTextInField(R.id.password, "XXXX")
        clickOn(R.id.bSignIn)
        Thread.sleep(3000)
    }

}

fun clickOn(id: Int) {
    onView(withId(id))
        .check(matches(isDisplayed()))
        .perform(click())
}

fun writeEditTextInField(id: Int, text: String) {
    onView(withId(id))
        .check(matches(isDisplayed()))
        .perform(typeText(text))
}

