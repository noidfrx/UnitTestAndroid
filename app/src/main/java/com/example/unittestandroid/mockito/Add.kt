package com.example.unittestandroid.mockito

class Add(var validNumber: ValidNumber, var print: Print) {

    /*Sumamos solo si es entero entre 0 y 9
        add depende de validNumber
        QUEREMOS TESTEAR QUE FUNCIONE ADD, DAMOS POR HECHO QUE VALIDNUMBER FUNCIONA
        USAMOS MOCK POR LO QUE NO VAMOS A USAR VALIDNUMBER COMO TAL, PROGRAMAMOS COMPORTAMIENTO
     */
    fun add(a: Any, b: Any): Int{
        return if(validNumber.check(a)&&validNumber.check(b)){
            a as Int + b as Int
        } else 0
    }

    fun addInt(a: Any?): Int{
        return  validNumber!!.doubleToInt(a)*2
    }

    /*
    *   Usamos validNumber para comprobar
    *   Usamos print para error e imprimir
    * */
    fun addPrint(a: Any, b: Any){
        if(validNumber!!.check(a)&&validNumber!!.check(b)){
            var result = a as Int + b as Int
            print.showMessage(result)
        }else{
            print.showError()
        }
    }
}