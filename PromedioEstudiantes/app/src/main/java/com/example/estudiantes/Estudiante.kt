package com.example.estudiantes

// Clase que representa a un estudiante con nombre y cinco notas
class Estudiante(
    val nombre: String, // Nombre del estudiante
    private val nota1: Double,
    private val nota2: Double,
    private val nota3: Double,
    private val nota4: Double,
    private val nota5: Double
) {

    // Bloque de inicialización que valida las notas
    init {
        // Asegura que todas las notas estén en el rango permitido de 0 a 10
        require(nota1 in 0.0..10.0) { "La Nota 1 debe estar entre 0 y 10" }
        require(nota2 in 0.0..10.0) { "La Nota 2 debe estar entre 0 y 10" }
        require(nota3 in 0.0..10.0) { "La Nota 3 debe estar entre 0 y 10" }
        require(nota4 in 0.0..10.0) { "La Nota 4 debe estar entre 0 y 10" }
        require(nota5 in 0.0..10.0) { "La Nota 5 debe estar entre 0 y 10" }
    }

    // Método para calcular el promedio ponderado de las notas
    fun calcularPromedio(): Double {
        // Calcula el promedio usando los porcentajes especificados para cada nota
        return (nota1 * 0.15) + (nota2 * 0.15) + (nota3 * 0.20) +
                (nota4 * 0.25) + (nota5 * 0.25)
    }

    // Método para determinar el estado del estudiante basado en el promedio
    fun obtenerEstado(): String {
        // Obtiene el promedio y determina si el estudiante está aprobado o reprobado
        return if (calcularPromedio() >= 6.0) "Aprobado" else "Reprobado"
    }
}
