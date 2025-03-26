package com.example.calculadora
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C3E50)) // Fondo oscuro azulado
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "0",
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
                    .border(2.dp, Color(0xFF00C0FD), RoundedCornerShape(8.dp)) // Borde llamatico
                    .background(Color(0xFF34495E)) // Fondo más claro
                    .padding(24.dp),
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("7", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("8", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("9", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("/", Color(0xFFF39C12), modifier = Modifier.weight(1f)) // Naranja para dividir
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("4", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("5", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("6", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("*", Color(0xFFF39C12), modifier = Modifier.weight(1f)) // Naranja para multiplicar
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("1", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("2", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("3", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("-", Color(0xFFF39C12), modifier = Modifier.weight(1f)) // Naranja
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("0", Color(0xFF2980B9), modifier = Modifier.weight(2f)) // Azul
                CalculadoraButton(".", Color(0xFF2980B9), modifier = Modifier.weight(1f)) // Azul
                CalculadoraButton("=", Color(0xFF2ECC71), modifier = Modifier.weight(1f)) // Verde para igual
                CalculadoraButton("+", Color(0xFFF39C12), modifier = Modifier.weight(1f)) // Naranja
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculadoraButton("C", Color(0xFFE74C3C), modifier = Modifier.weight(1f)) // Rojo para borrar
                CalculadoraButton("DEL", Color(0xFFE74C3C), modifier = Modifier.weight(1f)) // Rojo para borrar
            }
        }
    }
}

@Composable
fun CalculadoraButton(
    text: String,
    color: Color,
    modifier: Modifier
) {
    Button(
        onClick = {},
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