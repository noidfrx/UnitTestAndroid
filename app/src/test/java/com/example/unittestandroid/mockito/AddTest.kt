package com.example.unittestandroid.mockito

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.stubbing.Answer
import java.lang.ArithmeticException
import java.lang.Exception
import java.util.ArrayList

class AddTest {

    @InjectMocks
    private val add: Add? = null

    @Mock
    private val validNumber : ValidNumber? = null

    @Mock
    private val print : Print? = null

    @Captor
    private val captor: ArgumentCaptor<Int>? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addTest(){
        //Patrón when -> ThenReturn

        //Mockeamos la respuesta de lo que me va a devolver
        `when`(validNumber!!.check(3)).thenReturn(true)
        var checkNumber = validNumber?.check(3)
        assertEquals(true, checkNumber)

        `when`(validNumber!!.check(-3)).thenReturn(false)
        var checkNumber2 = validNumber?.check(-3)
        assertEquals(false, checkNumber2)

    }

    @Test
    fun addMockExceptionTest(){

        //Mockeamos donde entregamos una excepcion
        `when`(validNumber!!.checkZero(0)).thenThrow(ArithmeticException("No podemos aceptar cero"))
        var exception: Exception? = null
        try {
            validNumber.checkZero(0)
        }catch (e: ArithmeticException){
            exception = e
        }

        assertNotNull(exception)
    }

    @Test
    fun addRealMethodTest(){
        //Se llama al método real cuando llega el 3 sin mockear mucho
        `when`(validNumber!!.check(3)).thenCallRealMethod()
        assertEquals(true, validNumber.check(3))

        `when`(validNumber.check("3")).thenCallRealMethod()
        assertEquals(false, validNumber.check("3"))

        //NO ESTÁ MOCKEADO
        //assertEquals(true, validNumber.check(4))
    }

    @Test
    fun addDoubleToIntThenAnswerTest(){
        //OBJETO QUE QUERAMOS: Permite devolver respuesta más completa de lo que necesitamos, podemos poner funciones
        val answer: Answer<Int> = Answer { 7 }
        `when`(validNumber!!.doubleToInt(7.777)).thenAnswer(answer)

        //Multiplica respuesta por 2 el 7 que debería devolver
        assertEquals(14, add!!.addInt(7.777))
    }

    /*
    *
    * Arrange:  Organizar all, condiciones que se ejecutará test
    * Act:      Actuación, método que queremos probar
    * Assert:   Afirmación, verificar test correcto
    *
    *
    * Given:    Condiciones ejecución test, SetUP
    * When:     Método que queremos probar, testear
    * Then:     Afirmación, verificar que test es correcto
    *
    * */

    @Test
    fun aaaPattern(){
        //Arrange: Hacer InjectMocks, Mock, Before/SetUp()
        `when`(validNumber?.check(4)).thenReturn(true)
        `when`(validNumber?.check(5)).thenReturn(true)

        //Act: Método a probar
        val result: Int = add!!.add(4,5)

        //Assert
        assertEquals(9, result)
    }

    @Test
    fun bddPattern(){
        //Given: Hacer InjectMocks, Mock, Before/SetUp()
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)

        //When: Método a probar
        val result: Int = add!!.add(4,5)

        //Then
        assertEquals(9, result)
    }

    @Test
    fun argumentMatcherTest(){
        //Given: CUALQUIER ENTERO NOS VA A DEVOLVER TRUE
        given(validNumber?.check(anyInt())).willReturn(true)

        //When: Método a probar
        val result: Int = add!!.add(4,5)

        //Then
        assertEquals(9, result)
    }

    @Test
    fun argumentPrintTest(){
        //Given:
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)


        //When: Método a probar
        val result: Int = add!!.add(4,5)

        //Then

        //VALIDA QUE SE LLAME AL VALID NUMBER CUANDO SE HACE ADD(4,4)
        //Mockito.verify(validNumber, times(2))?.check(4)
        Mockito.verify(validNumber,)?.check(4)
        Mockito.verify(validNumber,)?.check(5)

        //QUE CHECK CON 9 NO SEA LLAMADO
        verify(validNumber, Mockito.never())?.check(9)
        //QUE CHECK 4 SEA LLAMADO AL MENOS 1 VEZ
        verify(validNumber, Mockito.atLeast(1))?.check(4)
        //COMO MAXIMO 3 VECES
        verify(validNumber, Mockito.atMost(3))?.check(4)


        assertEquals(9, result)
    }

    @Test
    fun argumentPrinteandoTest(){
        //Given:
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)


        //When: Método a probar
        add!!.addPrint(4,5)

        //Then

        //VALIDA QUE SE LLAME AL VALID NUMBER CUANDO SE HACE ADD(4,4)
        //Mockito.verify(validNumber, times(2))?.check(4)
        Mockito.verify(validNumber,)?.check(4)
        Mockito.verify(validNumber,)?.check(5)

        //QUE CHECK CON 9 NO SEA LLAMADO
        verify(validNumber, Mockito.never())?.check(9)
        //QUE CHECK 4 SEA LLAMADO AL MENOS 1 VEZ
        verify(validNumber, Mockito.atLeast(1))?.check(4)
        //COMO MAXIMO 3 VECES
        verify(validNumber, Mockito.atMost(3))?.check(4)


        //SE PUEDE ANALIZAR COMPORTAMIENTO INTERNO DE LO DE ARRIBA AUNQUE NO DEVUELVA NADA
        verify(print)!!.showMessage(9)
        verify(print, Mockito.never())!!.showError()


    }

    @Test
    fun captorTest(){
        //Given
        given(validNumber?.check(4)).willReturn(true)
        given(validNumber?.check(5)).willReturn(true)
        //When
        add!!.addPrint(4,5)
        //Then
        verify(print)!!.showMessage(captor!!.capture())
        assertEquals(9, captor.value)
    }


    //--- SPY VS MOCK ---

    @Spy    //Es una instancia real de un arraylist
    var spyList: MutableList<String> = ArrayList()

    @Mock   //No es instancia real
    var moclList: MutableList<String> = ArrayList()

    @Test
    fun spyTest(){
        spyList.add("1")
        spyList.add("2")

        //Verificamos que se llama al metodo add con valor 1 y 2
        verify(spyList).add("1")
        verify(spyList).add("2")

        //NOS DA 2 PORQUE REALMENTE INSTANCIÓ LO NECESARIO
        //SI TENEMOS QUE LLAMAR A METODOS REALES DE DEPENDENCIAS Y INTEGRACIONES NOS CONVIENE SPY
        assertEquals(2, spyList.size)
    }

    @Test
    fun mockTest(){
        moclList.add("1")
        moclList.add("2")

        verify(moclList).add("1")
        verify(moclList).add("2")

        given(moclList.size).willReturn(2)
        //COMO NO MOCKEAMOS EL COMPORTAMIENTO DARÍA UN 0
        //assertEquals(0, moclList.size)
        assertEquals(2, moclList.size)

    }

}