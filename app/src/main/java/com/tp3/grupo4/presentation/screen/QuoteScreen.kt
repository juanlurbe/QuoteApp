package com.tp3.grupo4.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tp3.grupo4.presentation.navigation.Routes
import com.tp3.grupo4.presentation.viewmodel.QuoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    viewModel: QuoteViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val phrase by viewModel.phrase
    val author by viewModel.author
    val category by viewModel.category
    val isLoading by viewModel.isLoading

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Carga la frase cuando se inicia la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadRandomQuote()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Frase del día") },
                actions = {
                    IconButton(onClick = { navController.navigate(Routes.FAVORITES) }) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Ver favoritos")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.saveCurrentQuoteAsFavorite()
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Frase guardada en favoritos")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Guardar favorita")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = "💬 $phrase", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "🧑 $author", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "📚 $category", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.loadRandomQuote() },
                    enabled = !isLoading
                ) {
                    Text(text = "Actualizar frase")
                }
            }
        }
    }
}
