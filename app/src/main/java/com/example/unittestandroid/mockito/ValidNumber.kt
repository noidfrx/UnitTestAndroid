package com.example.unittestandroid.mockito

import java.lang.ArithmeticException

class ValidNumber {
    //True de 0..9 --- False otra cosa
    fun check(o: Any?):Boolean{
        return if(o is Int){
            o in 0..9
        }else{
            false
        }
    }

    /*  Zero:               exception
    *   notInt notZero:     false
    *   Int and notZero :   true
    * */
    fun checkZero(o:Any): Boolean{
        return if(o is Int){
            if(o == 0){
                throw  ArithmeticException("No podemos aceptar cero")
            }else{
                true
            }
        }else{
            false
        }
    }

    /*  Double:             Int del número
    *   Cualquier cosa :    0
    * */
    fun doubleToInt(o: Any?): Int{
        return if(o is Double){
            o.toInt()
        }else{
            0
        }
    }
}