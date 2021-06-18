package com.dicoding.proyekakhir.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.proyekakhir.R.id
import com.dicoding.proyekakhir.core.utils.DummyData
import com.dicoding.proyekakhir.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val moviesDummyData = DummyData.getMovies()
    private val tvShowsDummyData = DummyData.getTvShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun movies() {
        onView(withId(id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummyData.size))
        onView(withId(id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(id.img_poster)).check(matches(isDisplayed()))
        onView(withId(id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(id.tv_title)).check(matches(isDisplayed()))
        onView(withId(id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(id.btn_watchlist)).perform(click())

        pressBack()
        onView(withId(id.toolbar)).perform(click())

        onView(withId(id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(id.img_poster)).check(matches(isDisplayed()))
        onView(withId(id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(id.tv_title)).check(matches(isDisplayed()))
        onView(withId(id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(id.btn_watchlist)).perform(click())

        pressBack()
    }

    @Test
    fun tvShows() {
        onView(withId(id.nav_tv_shows)).perform(click())

        onView(withId(id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShowsDummyData.size))
        onView(withId(id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(id.img_poster)).check(matches(isDisplayed()))
        onView(withId(id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(id.tv_title)).check(matches(isDisplayed()))
        onView(withId(id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(id.btn_watchlist)).perform(click())

        pressBack()
        onView(withId(id.toolbar)).perform(click())

        onView(withText("TV Shows")).perform(click())
        onView(withId(id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(id.img_poster)).check(matches(isDisplayed()))
        onView(withId(id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(id.tv_title)).check(matches(isDisplayed()))
        onView(withId(id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(id.btn_watchlist)).perform(click())

        pressBack()
    }

    @Test
    fun about() {
        onView(withId(id.nav_about)).perform(click())

        pressBack()
    }
}