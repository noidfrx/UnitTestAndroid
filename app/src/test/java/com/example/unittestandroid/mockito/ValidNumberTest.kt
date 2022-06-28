package com.example.unittestandroid.mockito

import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.ArithmeticException

class ValidNumberTest {
    private var validNumber: ValidNumber? = null

    @Before
    fun setUp(){
        validNumber = ValidNumber()
    }

    @After
    fun tearDown(){
        validNumber = null
    }

    @Test
    fun checkTest(){
        assertEquals( true, validNumber!!.check(5))
    }

    @Test
    fun checkNegativeTest(){
        assertEquals(false, validNumber!!.check(-5))
    }

    @Test
    fun checkStringTest(){
        assertEquals(false, validNumber!!.check("5"))
    }

    @Test
    fun checkDoubleTest(){
        assertEquals(false, validNumber!!.check(5.5))
    }

    @Test
    fun checkZeroTest(){
        assertEquals(true, validNumber!!.check(0))
    }

    @Test
    fun checkZerooTest(){
        assertEquals(true, validNumber!!.checkZero(-1))
    }

    @Test
    fun checkZeroooTest(){
        assertEquals(false, validNumber!!.checkZero("zero"))
    }

    @Test(expected = ArithmeticException::class)
    fun checkZerooooTest(){
        assertEquals(false, validNumber!!.checkZero(0))
    }

    @Test
    fun doubleToIntTest(){
        assertEquals(9, validNumber?.doubleToInt(9.999))
    }
    @Test
    fun doubleToIntErrorTest(){
        assertEquals(0, validNumber?.doubleToInt("9.999"))
    }
}