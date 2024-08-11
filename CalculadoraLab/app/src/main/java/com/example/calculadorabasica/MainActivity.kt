package com.example.calculadorabasica

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

// Actividad principal que gestiona la interfaz de usuario y la lógica de cálculo.
class MainActivity : AppCompatActivity() {

    // Variables para los componentes de la interfaz de usuario
    private lateinit var editTextOperando1: EditText
    private lateinit var editTextOperando2: EditText
    private lateinit var textViewResultado: TextView
    private lateinit var spinnerOperaciones: Spinner
    private lateinit var buttonCalcular: Button

    // Instancia de la clase Calculadora
    private lateinit var calculadora: Calculadora

    // Variable para almacenar la operación seleccionada en el Spinner
    private var operacionSeleccionada: Int = 0

    // Método que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar los componentes de la interfaz de usuario
        editTextOperando1 = findViewById(R.id.editTextOperando1)
        editTextOperando2 = findViewById(R.id.editTextOperando2)
        textViewResultado = findViewById(R.id.textViewResultado)
        spinnerOperaciones = findViewById(R.id.spinnerOperaciones)
        buttonCalcular = findViewById(R.id.buttonCalcular)

        // Crear una instancia de la calculadora
        calculadora = Calculadora()

        // Configurar el Spinner con las opciones de operación
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.operaciones_array, // Recurso de cadena que contiene las operaciones
            android.R.layout.simple_spinner_item // Diseño para cada elemento del Spinner
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Diseño del dropdown
        }
        spinnerOperaciones.adapter = adapter // Establecer el adaptador en el Spinner

        // Configurar el Listener para el Spinner
        spinnerOperaciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // Este método se llama cuando se selecciona un elemento en el Spinner
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                // Guardar la posición del elemento seleccionado
                operacionSeleccionada = position
            }

            // Este método se llama cuando no se selecciona ningún elemento (generalmente no se usa)
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada si no se selecciona ninguna opción
            }
        }

        // Configurar el Listener para el botón Calcular
        buttonCalcular.setOnClickListener {
            // Llamar al método para realizar la operación seleccionada cuando se haga clic en el botón
            realizarOperacion(operacionSeleccionada)
        }
    }

    // Método para realizar la operación matemática
    private fun realizarOperacion(operacionIndex: Int) {
        try {
            // Obtener los valores de los operandos desde los campos de texto y convertirlos a Double
            val operando1 = editTextOperando1.text.toString().toDouble()
            val operando2 = editTextOperando2.text.toString().toDouble()

            // Realizar la operación matemática basada en la selección del Spinner
            val resultado = when (operacionIndex) {
                0 -> calculadora.sumar(operando1, operando2) // Suma
                1 -> calculadora.restar(operando1, operando2) // Resta
                2 -> calculadora.multiplicar(operando1, operando2) // Multiplicación
                3 -> calculadora.dividir(operando1, operando2) // División
                else -> throw IllegalArgumentException("Operación desconocida") // Manejo de error si la operación no es reconocida
            }

            // Formatear el resultado de acuerdo con la operación
            // Si la operación es división, se usa un formato con varios decimales
            val resultadoFormateado = if (operacionIndex == 3) {
                DecimalFormat("#.######").format(resultado)
            } else {
                // Para otras operaciones, se muestra como un número entero
                resultado.toInt().toString()
            }

            // Mostrar el resultado en el TextView
            textViewResultado.text = "Resultado: $resultadoFormateado"
        } catch (e: NumberFormatException) {
            // Manejar el error si los operandos no son números válidos
            textViewResultado.text = "Error: Ingrese números válidos"
        } catch (e: ArithmeticException) {
            // Manejar el error si ocurre una excepción matemática, como la división por cero
            textViewResultado.text = "Error: ${e.message}"
        } catch (e: IllegalArgumentException) {
            // Manejar el error si la operación no es reconocida
            textViewResultado.text = "Error: Operación desconocida"
        }
    }
}
