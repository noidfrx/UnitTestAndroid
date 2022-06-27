package com.example.unittestandroid.junit5

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.ArithmeticException

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

    @After
    fun tearDown(){
        calculator = null
        println("@After -> tearDown()")
    }

    //@Test Indicamos que es un método de prueba llamando al método que queremos testear
    @Test
    fun calculatorNotNullTest(){
        //Comprueba que calculadora no sea nula, se manda mensaje para cuando ocurra error
        assertNotNull("Calculator debe ser not null",calculator)
        println("@Test -> calculatorNotNullTest()")

    }

    @Test
    fun `necesitamosInstanciaDeCalculatorParaQueClaseCalculatorFuncione`(){
        assertNotNull("necesitamosInstancia()",calculator)
        println("@Test -> second()")
    }
    /*Suma
    * 10 20 = 30*/
    @Test
    fun addAssertTest(){
        //Setup
        val calculatorAssert = Calculator()
        val resEsperado = 30
        val resActual: Int
        //Action
        resActual = calculatorAssert.add(10,20)
        //Assert
        assertEquals(resEsperado,resActual)
        println("TEST AddAssertTest")
    }

    @Test
    fun addTest(){
        assertEquals(30, calculator?.add(10,20))
    }

    @Test
    fun assertTypes(){
        assertTrue(1==1)
        assertFalse(1==2)
        println("AssertTypes()")
    }

    @Test
    fun `necesitamos_que_se_divida`(){
        assertEquals(2, calculator?.divide(4,2))
        println("divide()")
    }

    @Test
    fun `necesitamos_que_se_divida_not_valid`(){
        fail("Fallo detectado manualmente, no se puede dividir entre 0 solucionado")
        assertEquals(2, calculator?.divide(5,0))
        println("divideNotValid()")
    }

    @Test(expected = ArithmeticException::class)
    fun `divide_by_zero_valid_captura_exepcion`(){
        assertEquals(2, calculator?.divide(5,0))
        println("conExcepcion()")
    }
}