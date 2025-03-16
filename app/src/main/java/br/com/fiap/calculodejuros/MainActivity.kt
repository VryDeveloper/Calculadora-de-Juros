package br.com.fiap.calculodejuros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.calculodejuros.calculos.calcularJuros
import br.com.fiap.calculodejuros.calculos.calcularMontante
import br.com.fiap.calculodejuros.components.CaixaDeEntrada
import br.com.fiap.calculodejuros.components.CardResultado
import br.com.fiap.calculodejuros.ui.theme.CalculodejurosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculodejurosTheme { // Corrected name here!
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JurosScreen()
                }
            }
        }
    }
}

@Composable
fun JurosScreen() {

    var capital by remember { mutableStateOf("") }
    var taxa by remember { mutableStateOf("") }
    var tempo by remember { mutableStateOf("") }
    var juros by remember { mutableStateOf(0.0) }
    var montante by remember { mutableStateOf(0.0) }

    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF4CAF50),
                )
            ) {
                Text(
                    text = "Cálculo de Juros Simples",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .wrapContentSize(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            // Formulário para entrada de dados
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Dados do Investimento",
                        fontWeight = FontWeight.Bold
                    )
                    // Caixas de entrada da aplicação

                    CaixaDeEntrada(
                        label = "Valor do investimento",
                        value = capital,
                        keyboardType = KeyboardType.Decimal,
                        placeholder = "Quanto deseja investir",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        atualizarValor = { capital = it }
                    )
                    CaixaDeEntrada(
                        label = "Taxa de juros mensal",
                        value = taxa,
                        keyboardType = KeyboardType.Decimal,
                        placeholder = "Qual a taxa de juros mensal?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        atualizarValor = { taxa = it }
                    )
                    CaixaDeEntrada(
                        label = "Periodo em meses",
                        value = tempo,
                        keyboardType = KeyboardType.Number,
                        placeholder = "Qual o tempo em meses?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        atualizarValor = { tempo = it }
                    )
//
                    Button(
                        onClick = {
                            juros = calcularJuros(
                                capital = capital.toDouble(),
                                taxa = taxa.toDouble(),
                                tempo = tempo.toDouble()
                            )
                            montante = calcularMontante(
                                capital = capital.toDouble(),
                                juros = juros
                            )
                        },
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .height(48.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color(
                            0xFF286E2C
                        )
                        )
                    ) {
                        Text(text = "CALCULAR")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Resultado da aplicação
            CardResultado(juros = juros, montante = montante)
        }
    }
}
