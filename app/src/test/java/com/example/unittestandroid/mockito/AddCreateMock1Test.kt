package com.example.unittestandroid.mockito

import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class AddCreateMock1Test{

    private var add: Add? = null
    private var validNumber : ValidNumber? = null
    private var print : Print?=null

    @Before
    fun setUp(){
        //Se mockea la clase de validNumber, por lo que podremos programar comportamiento
        validNumber = Mockito.mock(ValidNumber::class.java)
        print = Mockito.mock(Print::class.java)
        add = Add(validNumber!!, print!!)
    }

    @Test
    fun addTest(){
        //Ejecutar codigo de clase bajo test
        add?.add(3,2)
        //Verify: si sucedió cierto comportamiento dentro del Mock
        Mockito.verify(validNumber)?.check(3)
        //Mockito.verify(validNumber)?.check(2) //Nunca es llamado en el código porque devuelve false el primero

    }

    @After
    fun tearDown(){
        add = null
    }
}