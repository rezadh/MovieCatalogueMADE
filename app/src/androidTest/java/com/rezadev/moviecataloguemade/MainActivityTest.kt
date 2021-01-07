@file:Suppress("DEPRECATION")

package com.rezadev.moviecataloguemade

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.rezadev.core.utils.EspressoIdlingResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("DEPRECATION")
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Before
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.rv_movies)).perform(ViewActions.swipeUp()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delayOneSecond()
        Espresso.onView(withId(R.id.rv_movies)).perform(ViewActions.swipeDown()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delayOneSecond()
    }

    @Test
    fun loadMovieTv() {
        Espresso.onView(ViewMatchers.withText("Movie Tv")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_movies_tv)).perform(ViewActions.swipeUp()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delayOneSecond()
        Espresso.onView(withId(R.id.rv_movies_tv)).perform(ViewActions.swipeDown()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delayOneSecond()
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movies)).perform(ViewActions.click())
        delayOneSecond()
        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click())
        delayOneSecond()
    }

    @Test
    fun loadDetailMovieTV() {
        Espresso.onView(ViewMatchers.withText("Movie Tv")).perform(ViewActions.click())
        delayOneSecond()
        Espresso.onView(withId(R.id.rv_movies_tv)).perform(ViewActions.click())
        delayOneSecond()
        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click())
        delayOneSecond()
    }

    private fun delayOneSecond() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}