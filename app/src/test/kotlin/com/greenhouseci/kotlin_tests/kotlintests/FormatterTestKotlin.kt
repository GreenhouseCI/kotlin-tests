package com.greenhouseci.kotlin_tests.kotlintests

import org.junit.Assert.assertEquals
import org.junit.Test


class FormatterTestKotlin {

    private val formatter: Formatter = Formatter()

    infix fun Any.equals(expected: Any) {
        assertEquals(expected, this)
    }

    @Test
    @Throws(Exception::class)
    fun testStripLeft() {
        val reference = "my string    "
        val testStrings = arrayOf(
            reference,
            " $reference",
            "  $reference",
            " \t $reference",
            " \n  $reference"
        )
        testStrings.map { formatter.stripLeft(it) equals reference }
    }

    @Test
    @Throws(Exception::class)
    fun testStripRight() {
        val reference = "    my string"
        val testStrings = arrayOf(
            reference,
            "$reference ",
            "$reference  ",
            "$reference \t ",
            "$reference \n  "
    )
        testStrings.map { formatter.stripRight(it) equals reference }
    }

    @Test
    @Throws(Exception::class)
    fun testStrip() {
        val reference = "my string"
        val testStrings = arrayOf(
            reference,
            " $reference ",
            " $reference  ",
            " \t $reference \t ",
            " \n  $reference \n ",
            "\tmy \n string\t"
        )
        testStrings.map { formatter.strip(it) equals reference }
    }

    @Test
    @Throws(Exception::class)
    fun testRemoveDuplicateWhitespaces() {
        val reference = "my reference string"
        val testStrings = arrayOf(
            reference,
            "my  reference string",
            "my reference  string",
            "my  reference  string",
            "my   reference  string",
            "my \treference\t\n string",
            "my \t\t\treference \n\n\nstring"
        )
        testStrings.map { formatter.stripMiddle(it) equals reference }
    }
}
