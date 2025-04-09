package com.example.calculadora
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.rem
import kotlin.text.toInt
import kotlin.toString


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                CalculadoraDiseño()
            }
        }
    }
}

@Composable
fun CalculadoraDiseño() {
    // Estado para el texto del display
    var displayText by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C3E50)) // Fondo oscuro azulado
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween // Espaciado entre elementos
    ) {
        // Display
        Text(
            text = displayText,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Ocupa más espacio vertical
                .background(Color(0xFF34495E)) // Fondo más claro para el display
                .padding(24.dp),
            color = Color.White,
            fontSize = 64.sp, // Tamaño de fuente más grande
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )

        // Espaciador para evitar superposición
        Spacer(modifier = Modifier.height(16.dp))

        // Botones
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Fila 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("C", Color(0xFFE74C3C), Modifier.weight(1f)) {
                    displayText = "0"
                }
                CalculadoraButton("/", Color(0xFFF39C12), Modifier.weight(1f)) {
                    displayText += "/"
                }
                CalculadoraButton("*", Color(0xFFF39C12), Modifier.weight(1f)) {
                    displayText += "*"
                }
                CalculadoraButton("DEL", Color(0xFFE74C3C), Modifier) {
                    displayText = if (displayText.length > 1) {
                        displayText.dropLast(1)
                    } else {
                        "0"
                    }
                }
            }
            // Fila 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("7", "8", "9", "-").forEach { text ->
                    CalculadoraButton(text, if (text == "-") Color(0xFFF39C12) else Color(0xFF2980B9), Modifier.weight(1f)) {
                        displayText = if (displayText == "0") text else displayText + text
                    }
                }
            }
            // Fila 3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("4", "5", "6", "+").forEach { text ->
                    CalculadoraButton(text, if (text == "+") Color(0xFFF39C12) else Color(0xFF2980B9), Modifier.weight(1f)) {
                        displayText = if (displayText == "0") text else displayText + text
                    }
                }
            }
            // Fila 4
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("1", "2", "3", "=").forEach { text ->
                    CalculadoraButton(text, if (text == "=") Color(0xFF2ECC71) else Color(0xFF2980B9), Modifier.weight(1f)) {
                        if (text == "=") {
                            try {
                                displayText = evalExpression(displayText)
                            } catch (e: Exception) {
                                displayText = "Error"
                            }
                        } else {
                            displayText = if (displayText == "0") text else displayText + text
                        }
                    }
                }
            }
            // Fila 5
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("0", Color(0xFF2980B9), Modifier.weight(2f)) {
                    displayText = if (displayText == "0") "0" else displayText + "0"
                }
                CalculadoraButton(".", Color(0xFF2980B9), Modifier) {
                    if (!displayText.contains(".")) {
                        displayText += "."
                    }
                }
            }
        }
    }
}

// Función para evaluar expresiones matemáticas
fun evalExpression(expression: String): String {
    return try {
        val result = ExpressionBuilder(expression).build().evaluate()
        if (result % 1 == 0.0) {
            result.toInt().toString() // Si es entero, convertir a Int
        } else {
            String.format("%.2f", result) // Limitar a 2 decimales
        }
    } catch (e: Exception) {
        "Error"
    }
}

@Composable
fun CalculadoraButton(
    text: String,
    color: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculadoraTheme {
        CalculadoraDiseño()
    }
}