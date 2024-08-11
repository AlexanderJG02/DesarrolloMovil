package com.example.estudiantes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Definición de variables para los campos de texto y botón en la interfaz
    private lateinit var editTextNombre: EditText
    private lateinit var editTextNota1: EditText
    private lateinit var editTextNota2: EditText
    private lateinit var editTextNota3: EditText
    private lateinit var editTextNota4: EditText
    private lateinit var editTextNota5: EditText

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Establece el layout para esta actividad

        // Inicialización de los campos de texto y botón a partir de sus IDs en el XML
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextNota1 = findViewById(R.id.editTextNota1)
        editTextNota2 = findViewById(R.id.editTextNota2)
        editTextNota3 = findViewById(R.id.editTextNota3)
        editTextNota4 = findViewById(R.id.editTextNota4)
        editTextNota5 = findViewById(R.id.editTextNota5)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)

        // Configura el botón para que al hacer clic llame al método calcularPromedio
        buttonCalcular.setOnClickListener { calcularPromedio() }
    }

    // Método para calcular el promedio de las notas
    private fun calcularPromedio() {
        try {
            // Obtiene el nombre y las notas del usuario
            val nombre = editTextNombre.text.toString()
            val nota1 = editTextNota1.text.toString().toDouble()
            val nota2 = editTextNota2.text.toString().toDouble()
            val nota3 = editTextNota3.text.toString().toDouble()
            val nota4 = editTextNota4.text.toString().toDouble()
            val nota5 = editTextNota5.text.toString().toDouble()

            // Crea una instancia de la clase Estudiante con los datos proporcionados
            val estudiante = Estudiante(nombre, nota1, nota2, nota3, nota4, nota5)
            // Calcula el promedio y determina el estado del estudiante
            val promedio = estudiante.calcularPromedio()
            val estado = estudiante.obtenerEstado()

            // Muestra el resultado en un diálogo
            showResultDialog(nombre, promedio, estado)
        } catch (e: NumberFormatException) {
            // Muestra un mensaje de error si alguna nota no es un número válido
            Toast.makeText(this, "Error: Ingrese valores válidos para las notas.", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalArgumentException) {
            // Muestra un mensaje de error si ocurre una excepción de argumento inválido
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para mostrar el resultado en un diálogo
    private fun showResultDialog(nombre: String, promedio: Double, estado: String) {
        // Infla el layout del diálogo personalizado
        val dialogView = layoutInflater.inflate(R.layout.dialog_result, null)
        // Obtiene referencias a los TextView en el layout del diálogo
        val textViewNombre = dialogView.findViewById<TextView>(R.id.textViewNombre)
        val textViewPromedio = dialogView.findViewById<TextView>(R.id.textViewPromedio)
        val textViewEstado = dialogView.findViewById<TextView>(R.id.textViewEstado)

        // Establece el texto de los TextView con los datos proporcionados
        textViewNombre.text = "Nombre: $nombre"
        textViewPromedio.text = "Promedio: $promedio"
        textViewEstado.text = "Estado: $estado"

        // Cambia el color del estado basado en si el estudiante está aprobado o reprobado
        textViewEstado.setTextColor(
            if (estado == "Aprobado") resources.getColor(android.R.color.holo_blue_light)
            else resources.getColor(android.R.color.holo_red_light)
        )

        // Crea y muestra un diálogo con el resultado
        AlertDialog.Builder(this)
            .setTitle("Resultado") // Título del diálogo
            .setView(dialogView) // Establece el contenido del diálogo
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() } // Botón para cerrar el diálogo
            .show() // Muestra el diálogo
    }
}
