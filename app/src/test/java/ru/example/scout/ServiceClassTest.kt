package ru.example.scout

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ServiceClass {
    private val output = ByteArrayOutputStream()
    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(output))
    }

    @Test
    fun testString() {
        val example = OutputText()
        example.SomeText()
        Assert.assertEquals("Test string", output.toString())
    }

    @Test
    fun testLoading() {
        val myViewState = MyViewState()
        val myViewStateCopy = myViewState.copy(loading = true)
        Assert.assertEquals(myViewStateCopy.loading,true)
    }

    @After
    fun cleanUpStreams() {
        System.setOut(null)
    }

}