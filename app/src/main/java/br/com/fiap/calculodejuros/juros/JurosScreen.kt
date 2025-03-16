package br.com.fiap.calculodejuros.juros

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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


@Composable
fun JurosScreen(jurosScreenViewModel: JurosScreenViewModel) {


    val capital by jurosScreenViewModel.capital.observeAsState("")
    val taxa by jurosScreenViewModel.taxa.observeAsState("")
    val tempo by jurosScreenViewModel.tempo.observeAsState("")
    val juros by jurosScreenViewModel.juros.observeAsState(0.0)
    val montante by jurosScreenViewModel.montante.observeAsState(0.0)



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
                        atualizarValor = {
                            jurosScreenViewModel.onCapitalChanged(it)
                        }
                    )
                    CaixaDeEntrada(
                        label = "Taxa de juros mensal",
                        value = taxa,
                        keyboardType = KeyboardType.Decimal,
                        placeholder = "Qual a taxa de juros mensal?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        atualizarValor = { jurosScreenViewModel.onTaxaChanged(it) }
                    )
                    CaixaDeEntrada(
                        label = "Periodo em meses",
                        value = tempo,
                        keyboardType = KeyboardType.Number,
                        placeholder = "Qual o tempo em meses?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        atualizarValor = { jurosScreenViewModel.onTempoChanged(it) }
                    )
//
                    Button(
                        onClick = {
                            jurosScreenViewModel.calcularJurosViewModel()
                            jurosScreenViewModel.calcularMontanteViewModel()
                        },
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .height(48.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            Color(
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