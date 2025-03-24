package com.example.galeriaarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeriaarte.ui.theme.GaleriaArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Permite que o conteúdo ocupe a tela toda, incluindo as bordas
        setContent {
            GaleriaArteTheme {
                GaleriaLayout()
            }
        }
    }
}
//Classe de dados que tem tres propriedades usadas para identificar cada imagem
data class PecaDeArte(
    @DrawableRes val imageRes: Int,
    @StringRes val titulo: Int,
    @StringRes val artista: Int
)
//Uma lista de objetos. cada um representa um conjunto da obra
val pecasDeArte = listOf(
    PecaDeArte(R.drawable.ponterionitero, R.string.tituloPonte, R.string.autorPonte),
    PecaDeArte(R.drawable.espelho, R.string.tituloEspelho, R.string.autorEspelho),
    PecaDeArte(R.drawable.montefuji, R.string.tituloFuji, R.string.autorFuji) // Corrigido o nome da imagem
)

@Composable
fun GaleriaLayout(modifier: Modifier = Modifier) {
    var etapaAtual by remember { mutableIntStateOf(0) }
    val pecaArte = pecasDeArte[etapaAtual] // Obter a peça de arte com base na etapa atual

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .padding(top = 75.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Box para a imagem
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(430.dp)
                .border(
                    width = 30.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RectangleShape
                )
                .shadow(20.dp)
        ) {
            Image(
                modifier = modifier
                    .fillMaxSize(),
                painter = painterResource(pecaArte.imageRes),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto do título e autor
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(pecaArte.titulo), fontSize = 20.sp)
            Text(text = stringResource(pecaArte.artista), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botões para navegar entre as imagens
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                etapaAtual = if (etapaAtual > 0) etapaAtual - 1 else pecasDeArte.size - 1
            }) {
                Text("Imagem anterior")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if (etapaAtual < pecasDeArte.size - 1) etapaAtual += 1
                else etapaAtual = 0
            }) {
                Text("Próxima imagem")
            }
        }
    }
}
