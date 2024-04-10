package org.company.app.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.stateholder.LocalSavedStateHolder
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_dark_mode
import multiplatform_app.composeapp.generated.resources.ic_light_mode
import multiplatform_app.composeapp.generated.resources.open_github
import multiplatform_app.composeapp.generated.resources.theme
import org.company.app.openUrl
import org.company.app.theme.LocalThemeIsDark
import org.company.app.viewModel.HomeViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.core.parameter.parametersOf

@Composable
fun HomeScene(
    goToSecondScene: (String) -> Unit,
) {
    val stateHolder = LocalSavedStateHolder.current
    val viewModel =
        koinViewModel(HomeViewModel::class) { parametersOf(9999, stateHolder) }
    val name = viewModel.name.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Greet Me!",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = name.value,
            maxLines = 1,
            label = {
                Text(
                    text = "Entered text",
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            onValueChange = { viewModel.setName(it) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
//                navigator.navigate(route = "/greeting/${name.value}")
                goToSecondScene.invoke(name.value)
            }
        ) {
            Text(text = "GO!")
        }
        Spacer(modifier = Modifier.height(30.dp))
        TextButton(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                .widthIn(min = 200.dp),
            onClick = { openUrl("https://kmm.icerock.dev/learning/intro") },
        ) {
            Text(stringResource(Res.string.open_github))
        }

        var isDark by LocalThemeIsDark.current
        val icon = remember(isDark) {
            if (isDark) Res.drawable.ic_light_mode
            else Res.drawable.ic_dark_mode
        }
        Spacer(modifier = Modifier.height(30.dp))
        ElevatedButton(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                .widthIn(min = 200.dp),
            onClick = { isDark = !isDark },
            content = {
                Icon(vectorResource(icon), contentDescription = null)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(Res.string.theme))
            }
        )
    }
}