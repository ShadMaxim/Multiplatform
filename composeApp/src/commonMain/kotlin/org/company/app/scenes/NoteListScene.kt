package org.company.app.scenes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import moe.tlaster.precompose.koin.koinViewModel
import org.company.app.data.Scenes
import org.company.app.viewModel.NoteListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun NoteListScene(
    onItemClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onAddClicked: () -> Unit,
) {
    val viewModel = koinViewModel(NoteListViewModel::class)

    val items by viewModel.items.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Note")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddClicked.invoke() }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
        LazyColumn {
            items(items.size, key = { it.hashCode() }) {
                ListItem(
                    headlineContent = {

                    },
                    modifier = Modifier
                        .clickable {
                            onItemClicked.invoke()
                        },
                    trailingContent = {
                        Row {
                            TextButton(
                                onClick = {
                                    onEditClicked.invoke()
                                },
                            ) {
                                Text("Edit")
                            }
                            TextButton(
                                onClick = {
                                    viewModel.delete(items[it])
                                },
                            ) {
                                Text("Delete", color = androidx.compose.ui.graphics.Color.Blue)
                            }
                        }
                    }
                )
            }
        }
    }
}