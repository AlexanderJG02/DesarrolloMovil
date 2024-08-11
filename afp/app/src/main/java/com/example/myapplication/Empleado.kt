package com.example.myapplication

class Empleado (val nombre: String, val salarioBase: Double) {

    fun calcularAFP(): Double {
        return salarioBase * 0.0725
    }

    fun calcularISSS(): Double {
        return salarioBase * 0.03
    }

    fun calcularRenta(): Double {
        return when {
            salarioBase > 472.00 && salarioBase <= 895.24 -> {
                (salarioBase - 472.00) * 0.10 + 17.67
            }
            salarioBase > 895.24 && salarioBase <= 2038.10 -> {
                (salarioBase - 895.24) * 0.20 + 60.00
            }
            salarioBase > 2038.10 -> {
                (salarioBase - 2038.10)
            }
            else -> 0.0
        }
    }

    fun calcularSalarioNeto(): Double {
        val afp = calcularAFP()
        val isss = calcularISSS()
        val renta = calcularRenta()

        return salarioBase - afp - isss - renta
    }
}