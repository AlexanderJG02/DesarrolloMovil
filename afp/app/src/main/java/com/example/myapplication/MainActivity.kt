package com.example.myapplication

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etSalarioBase: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etSalarioBase = findViewById(R.id.etSalarioBase)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        // Filtro para permitir solo letras en el nombre y establecer límite de caracteres
        val nombreMaxLength = 50
        etNombre.filters = arrayOf(onlyLettersFilter(), InputFilter.LengthFilter(nombreMaxLength))

        // No aplicamos un filtro para limitar el salario base a 5000
        // Aquí se puede escribir cualquier cantidad

        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString()
            val salarioBase = etSalarioBase.text.toString().replace("$", "").toDoubleOrNull()

            if (salarioBase != null) {
                val salarioMaximo = 5000.0

                if (salarioBase > salarioMaximo) {
                    // Mostrar mensaje de advertencia si el salario excede el máximo permitido
                    Toast.makeText(this, "El salario ingresado es mayor a $5000. No se realizará ningún cálculo.", Toast.LENGTH_LONG).show()

                    // Limpiar el TextView de resultados
                    tvResultado.text = ""
                } else {
                    // Realizar cálculos con el salario válido
                    val empleado = Empleado(nombre, salarioBase)
                    val afp = empleado.calcularAFP()
                    val isss = empleado.calcularISSS()
                    val renta = empleado.calcularRenta()
                    val salarioNeto = empleado.calcularSalarioNeto()

                    // Formateo de los resultados
                    val decimalFormat = DecimalFormat("#,##0.00")

                    val resultado = """
                        Nombre: $nombre
                        Salario Base: $${decimalFormat.format(salarioBase)}
                        AFP: -${decimalFormat.format(afp)}
                        Salario después de AFP: $${decimalFormat.format(salarioBase - afp)}
                        ISSS: -${decimalFormat.format(isss)}
                        Salario después de ISSS: $${decimalFormat.format(salarioBase - afp - isss)}
                        Renta: -${decimalFormat.format(renta)}
                        Salario Neto: $${decimalFormat.format(salarioNeto)}
                    """.trimIndent()

                    tvResultado.text = resultado
                }
            } else {
                tvResultado.text = "Por favor, ingrese un salario válido."
            }
        }
    }

    // Filtro para permitir solo letras en el EditText de nombre
    private fun onlyLettersFilter(): InputFilter {
        val pattern = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")

        return object : InputFilter {
            override fun filter(
                source: CharSequence,
                start: Int,
                end: Int,
                dest: Spanned,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                if (!pattern.matcher(source).matches()) {
                    return ""
                }
                return null
            }
        }
    }
}