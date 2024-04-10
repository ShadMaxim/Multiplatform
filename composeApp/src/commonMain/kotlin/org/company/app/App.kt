package org.company.app

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.stateholder.LocalSavedStateHolder
import multiplatform_app.composeapp.generated.resources.*
import org.company.app.theme.AppTheme
import org.company.app.theme.LocalThemeIsDark
import org.company.app.util.Platform
import org.company.app.viewModel.HomeViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.core.parameter.parametersOf
import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    PreComposeApp {
        KoinContext {
            val navigator = rememberNavigator()
            AppTheme {
                NavHost(
                    navigator = navigator,
                    initialRoute = "/home"
                ) {
                    scene(route = "/home") { backStackEntry ->

                        val stateHolder = LocalSavedStateHolder.current
                        val viewModel =
                            koinViewModel(HomeViewModel::class) { parametersOf(1236, stateHolder) }
                        val name = viewModel.name.collectAsStateWithLifecycle()
                        /*val viewModel = viewModel(modelClass = HomeViewModel::class,keys = listOf("")) {
                            HomeViewModel()
                        }
                        val name by viewModel.name.collectAsStateWithLifecycle()*/
                        var label = "1234"

                        backStackEntry.path<String>("name")?.let { nameQ ->
                            if (nameQ != null) {
                                label = nameQ
                            }
                        }
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
                                label = if (!label.isNullOrEmpty()) {
                                    {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                } else null,
                                onValueChange = { viewModel.setName(it) }
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(
                                onClick = {
                                    navigator.navigate(route = "/greeting/${name.value}")
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
                    /*scene(route = "/greeting/{name}") { backStackEntry ->
                        backStackEntry.path<String>("name")?.let { name ->
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                Button(onClick = { navigator.goBackWith("1234567") }) {
                                    Text(text = "GO BACK!")
                                }
                            }
                        }
                    }*/
                    dialog(route = "/greeting/{name}") { backStackEntry ->
                        backStackEntry.path<String>("name")?.let { name ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = MaterialTheme.colorScheme.onPrimary),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(30.dp))
//                            Button(onClick = { navigator.goBackWith(Model(id = "1", name = "qwerty", res = 900)) }) {
                                Button(onClick = { navigator.goBack() }) {
                                    Text(text = getPlatform().name)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/*data class Model(
    val id: String? = null,
    val name: String? = null,
    val res: Int? = null
)*/

/*class HomeViewModel : ViewModel() {
    val name = MutableStateFlow("")
    fun setName(value: String) {
        name.update { value }
    }
}*/
/*=
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.cyclone),
                fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
                style = MaterialTheme.typography.displayLarge
            )

            var isAnimate by remember { mutableStateOf(false) }
            val transition = rememberInfiniteTransition()
            val rotate by transition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing)
                )
            )

            Image(
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
                    .run { if (isAnimate) rotate(rotate) else this },
                imageVector = vectorResource(Res.drawable.ic_cyclone),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentDescription = null
            )

            ElevatedButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { isAnimate = !isAnimate },
                content = {
                    Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        stringResource(if (isAnimate) Res.string.stop else Res.string.run)
                    )
                }
            )

            var isDark by LocalThemeIsDark.current
            val icon = remember(isDark) {
                if (isDark) Res.drawable.ic_light_mode
                else Res.drawable.ic_dark_mode
            }

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

            TextButton(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { openUrl("https://kmm.icerock.dev/learning/intro") },
            ) {
                Text(stringResource(Res.string.open_github))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(44.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = MaterialTheme.shapes.small
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { *//*onCancelButtonClicked.invoke("987")*//* }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (getPlatform().name),
                    style = MaterialTheme.typography.bodyLarge
                        .copy(color = MaterialTheme.colorScheme.error),
                )
            }
        }
    }*/

internal expect fun openUrl(url: String?)
expect fun getPlatform(): Platform