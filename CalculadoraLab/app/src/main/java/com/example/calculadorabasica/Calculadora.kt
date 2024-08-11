package com.example.calculadorabasica

// Clase que define una calculadora básica con operaciones matemáticas
class Calculadora {

    // Método para sumar dos números
    fun sumar(operando1: Double, operando2: Double): Double {
        return operando1 + operando2
    }

    // Método para restar el segundo número del primero
    fun restar(operando1: Double, operando2: Double): Double {
        return operando1 - operando2
    }

    // Método para multiplicar dos números
    fun multiplicar(operando1: Double, operando2: Double): Double {
        return operando1 * operando2
    }

    // Método para dividir el primer número por el segundo
    fun dividir(operando1: Double, operando2: Double): Double {
        // Verificar si el divisor es cero para evitar la excepción de división por cero
        if (operando2 == 0.0) {
            // Lanzar una excepción si el divisor es cero
            throw ArithmeticException("División por cero no permitida")
        }
        // Realizar la división si el divisor no es cero
        return operando1 / operando2
    }
}
