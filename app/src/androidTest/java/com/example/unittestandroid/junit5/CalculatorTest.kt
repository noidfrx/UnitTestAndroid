package com.example.unittestandroid.junit5

import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/*Suma
* 10 20 = 30
* 7 4 = 11
*
* Resta
* 7 4 = 3
* 10 20 = -10
*
* Lo normal que haya 1 assert por método de prueba (True, null igual)
*
* @Test         Indicamos que es un método de prueba llamando al método que queremos testear
* @BeforeEach   Se ejecuta antes de cada test (Bueno para inicializar data de entrada y salida)
*
* */
internal class CalculatorTest{
    private var calculator: Calculator? = null

    //Inicializamos data
    @Before
    fun setUp(){
        calculator = Calculator()
        println("@BeforeEach -> setUp()")

    }

    //@Test Indicamos que es un método de prueba llamando al método que queremos testear
    @Test
    fun calculatorNotNullTest(){
        //Debemos instanciarlo para que no sea null
        calculator = Calculator()

        //Comprueba que calculadora no sea nula, se manda mensaje para cuando ocurra error
        assertNotNull("Calculator debe ser not null",calculator)
        println("@Test -> calculatorNotNullTest()")

    }

    @Test
    fun `necesitamosInstanciaDeCalculatorParaQueClaseCalculatorFuncione`(){
        assertNotNull("necesitamosInstancia()",calculator)
    }
}