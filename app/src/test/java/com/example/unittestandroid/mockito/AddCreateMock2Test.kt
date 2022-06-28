package com.example.unittestandroid.mockito

import org.junit.Before
import org.junit.Test
import org.mockito.*

class AddCreateMock2Test {

    //Objeto bajo pruebas al que se le inyectan los mock
    @InjectMocks
    private val add: Add? = null

    //Creamos mock
    @Mock
    private val validNumber: ValidNumber? = null

    @Mock
    private val print : Print? = null

    @Before
    fun setUp(){
        //Con esta linea el sujeto de pruebas va a tener un mock inyectado
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addTest(){
        add?.add(3,2)
        //Verify: si sucedió cierto comportamiento dentro del Mock
        Mockito.verify(validNumber)?.check(3)
    }

}