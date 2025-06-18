package com.tp3.grupo4.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tp3.grupo4.data.local.FavoriteQuoteEntity
import com.tp3.grupo4.presentation.viewmodel.FavoriteQuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteQuotesScreen(
    viewModel: FavoriteQuoteViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val favorites = viewModel.favoriteQuotes.collectAsState()
    var quoteToEdit by remember { mutableStateOf<FavoriteQuoteEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis frases favoritas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Mis frases favoritas",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(favorites.value) { quote ->
                    FavoriteQuoteItem(
                        quote = quote,
                        onEdit = { quoteToEdit = quote },
                        onDelete = { viewModel.removeFavorite(quote) }
                    )
                }
            }

            quoteToEdit?.let { quote ->
                EditFavoriteDialog(
                    quote = quote,
                    onDismiss = { quoteToEdit = null },
                    onConfirm = {
                        viewModel.editFavorite(it)
                        quoteToEdit = null
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteQuoteItem(
    quote: FavoriteQuoteEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = quote.phrase, style = MaterialTheme.typography.titleMedium)
                Text(text = "— ${quote.author}", style = MaterialTheme.typography.bodySmall)
                Text(text = "[${quote.category}]", style = MaterialTheme.typography.bodySmall)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFavoriteDialog(
    quote: FavoriteQuoteEntity,
    onDismiss: () -> Unit,
    onConfirm: (FavoriteQuoteEntity) -> Unit
) {
    var phrase by remember { mutableStateOf(quote.phrase) }
    var author by remember { mutableStateOf(quote.author) }
    var category by remember { mutableStateOf(quote.category) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar frase") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = phrase,
                    onValueChange = { phrase = it },
                    label = { Text("Frase") }
                )
                OutlinedTextField(
                    value = author,
                    onValueChange = { author = it },
                    label = { Text("Autor") }
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoría") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(quote.copy(phrase = phrase, author = author, category = category))
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}