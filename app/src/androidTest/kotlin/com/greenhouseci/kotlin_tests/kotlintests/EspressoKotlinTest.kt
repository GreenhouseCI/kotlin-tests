package com.greenhouseci.kotlin_tests.kotlintests


import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View

import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.equalToIgnoringCase


@RunWith(AndroidJUnit4::class)
class EspressoKotlinTest {

    private fun isViewDisplayed(viewMatcher: Matcher<View>) {
        onView(viewMatcher).check(matches(isDisplayed()))
    }

    private fun isNotDisplayed(viewMatcher: Matcher<View>) {
        onView(viewMatcher).check(
            matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        )
    }

    private fun addItemToList(item: String) {
        onView(withId(R.id.edit_item)).perform(clearText(), typeText(item))
        onView(withId(R.id.btn_add)).perform(click())
    }

    @Rule @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testAddButtonText() {
        val v = withId(R.id.btn_add)
        isViewDisplayed(v)
        onView(v).check(matches(withText(R.string.btn_add_item)))
    }

    @Test
    fun testInputText() {
        val v = withId(R.id.edit_item)
        isViewDisplayed(v)
        onView(v).check(matches(withText("")))
    }

    @Test
    fun initialStateTest() {
        val tv = withId(R.id.tv_empty)
        isViewDisplayed(tv)
        onView(tv).check(matches(withText(R.string.tv_no_items)))
    }

    @Test
    fun testNoItemsTextDisappearing() {
        val tv = withId(R.id.tv_empty)
        addItemToList("First item")
        isNotDisplayed(tv)
    }

    @Test
    fun testAddItemsToList() {
        val formatter = Formatter()
        val lv = onData(anything()).inAdapterView(withId(R.id.lv_items))

        var items = arrayOf(
            // Pair(input string, expected displayed value)
            Pair("    item 1", "item 1"),
            Pair("item 2  ", "item 2"),
            Pair("  item  \n3  ", "item 3")
        )

        items.map { pair -> addItemToList(pair.first) }
        items.mapIndexed { index, pair ->
            val expected = formatter.strip(pair.second)
            lv.atPosition(index).onChildView(withId(R.id.tv_list_item)).check(
                matches(withText(equalToIgnoringCase(expected)))
            )
        }
    }
}