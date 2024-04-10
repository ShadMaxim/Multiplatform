package org.company.app.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GreetingScene(
    value: String? = null,
    goToHomeScene: () -> Unit,
) {

    val result = remember {mutableStateOf(value)}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = result.value.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(30.dp))
//        Button(onClick = { navigator.goBackWith("1234567") }) {
        Button(onClick = { goToHomeScene.invoke() }) {
            Text(text = "GO BACK!")
        }
    }
}