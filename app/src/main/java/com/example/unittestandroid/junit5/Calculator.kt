package com.example.unittestandroid.junit5

import java.lang.ArithmeticException
import java.lang.Exception

class Calculator {

    //Suma enteros
    fun add(n1: Int, n2: Int): Int{
        return n1+n2
    }

    //Resta enteros
    fun substract(n1: Int, n2: Int): Int{
        return n1-n2
    }

    fun divide(n1: Int, n2:Int):Int{
        return n1/n2
    }

    fun divideByZero(n1: Int, n2:Int):Int{
        if(n2 == 0){
            throw ArithmeticException("No se puede dividir por 0")
        }
        return n1/n2
    }

    fun longTaskOperation(){
        try{
            Thread.sleep(1000)
        }catch (e:Exception){

        }
    }
}