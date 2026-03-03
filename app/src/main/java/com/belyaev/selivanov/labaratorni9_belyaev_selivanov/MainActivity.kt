package com.belyaev.selivanov.labaratorni9_belyaev_selivanov

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.belyaev.selivanov.labaratorni9_belyaev_selivanov.ui.theme.Labaratorni9_Belyaev_SelivanovTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labaratorni9_Belyaev_SelivanovTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameCatalogApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Game(
    val titleResourceId: Int,
    val descriptionResourceId: Int,
    val genreResourceId: Int,
    val yearResourceId: Int,
    val imageResourceId: Int
)

fun loadGameList(): List<Game> {
    return listOf(
        Game(
            R.string.game1_title,
            R.string.game1_description,
            R.string.game1_genre,
            R.string.game1_year,
            R.drawable.game1
        ),
        Game(
            R.string.game2_title,
            R.string.game2_description,
            R.string.game2_genre,
            R.string.game2_year,
            R.drawable.game2
        ),
        Game(
            R.string.game3_title,
            R.string.game3_description,
            R.string.game3_genre,
            R.string.game3_year,
            R.drawable.game3
        ),
        Game(
            R.string.game4_title,
            R.string.game4_description,
            R.string.game4_genre,
            R.string.game4_year,
            R.drawable.game4
        )
    )
}

@Composable
fun GameCatalogApp(modifier: Modifier = Modifier) {
    val games = remember { loadGameList() }

    var currentIndex by remember { mutableIntStateOf(0) }

    var showDescription by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Image(
                painter = painterResource(games[currentIndex].imageResourceId),
                contentDescription = stringResource(games[currentIndex].titleResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(games[currentIndex].titleResourceId),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(games[currentIndex].genreResourceId),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(games[currentIndex].yearResourceId),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (showDescription) {
            Text(
                text = stringResource(games[currentIndex].descriptionResourceId),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ControlPanel(
            currentIndex = currentIndex,
            totalItems = games.size,
            onPreviousClick = {
                if (currentIndex > 0) currentIndex--
            },
            onNextClick = {
                if (currentIndex < games.size - 1) currentIndex++
            },
            showDescription = showDescription,
            onToggleDescription = { showDescription = !showDescription }
        )
    }
}

@Composable
fun ControlPanel(
    currentIndex: Int,
    totalItems: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    showDescription: Boolean,
    onToggleDescription: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onPreviousClick,
                enabled = currentIndex > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Назад")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "${currentIndex + 1}/${totalItems}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = onNextClick,
                enabled = currentIndex < totalItems - 1,
                modifier = Modifier.weight(1f)
            ) {
                Text("Вперед")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onToggleDescription,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (showDescription) "Скрыть описание" else "Показать описание")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCatalogAppPreview() {
    Labaratorni9_Belyaev_SelivanovTheme {
        GameCatalogApp()
    }
}