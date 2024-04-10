package org.company.app

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.ExperimentalMaterialApi
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.company.app.theme.AppTheme
import org.company.app.util.Platform
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.company.app.data.Scenes.*
import org.company.app.data.Scenes.Companion.ARGUMENT
import org.company.app.scenes.GreetingScene
import org.company.app.scenes.HomeScene
import org.company.app.scenes.NoteListScene
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
                    initialRoute = Home().routeToDestination
                ) {

                    scene(route = Home().routeToDestination) { backStackEntry ->
                        HomeScene(
                            goToSecondScene = {
//                                navigator.navigate(route = "/greeting/${it}")
//                                navigator.navigate(route = "/greeting/${it}")
                                navigator.navigate(route = Greeting(argument = it).setRouteAndArgument)
//                                navigator.navigate(route = NoteList(argument = it).setRouteAndArgument)
                            }
                        )
                        /*val stateHolder = LocalSavedStateHolder.current
                        val viewModel =
                            koinViewModel(HomeViewModel::class) { parametersOf(9999, stateHolder) }
                        val name = viewModel.name.collectAsStateWithLifecycle()
                        var label = "1234"

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
                        }*/
                    }

//                    scene(route = "/greeting/{name}") { backStackEntry ->
                    scene(route = Greeting().routeToDestination) { backStackEntry ->
                        backStackEntry.path<String>(ARGUMENT)?.let { name: String? ->
//                        backStackEntry.path<String>(ARGUMENT)?.let { name ->
                            GreetingScene(
                                value = name.orEmpty(),
//                                goToHomeScene = {navigator.goBack()}
                                goToHomeScene = { navigator.navigate(route = NoteList(argument = name).setRouteAndArgument) }
                            )
                        }
                    }

                    scene(
                        route = NoteList().routeToDestination,
                        navTransition = NavTransition(
                            createTransition = slideInVertically(initialOffsetY = { it }),
                            destroyTransition = slideOutVertically(targetOffsetY = { it }),
                            pauseTransition = scaleOut(targetScale = 0.9f),
                            resumeTransition = scaleIn(initialScale = 0.9f),
                            exitTargetContentZIndex = 1f,
                        )
                    ) { backStackEntry ->
                        backStackEntry.path<String>(ARGUMENT)?.let { name: String? ->
                            NoteListScene(
                                onEditClicked = {},
                                onAddClicked = {},
                                onItemClicked = {
                                    /*navigator.navigate(
                                        route = Home().setRouteAndArgument,
                                        options = NavOptions(
                                            popUpTo = PopUpTo(
                                                route = Greeting().setRouteAndArgument,
                                                inclusive = true,
                                            )
                                        )
                                    )*/
                                    /*navigator.goBack(
                                        popUpTo = PopUpTo(
                                            route = Home().setRouteAndArgument,
                                            inclusive = false
                                        )
                                    )*/
                                    navigator.navigate(
                                        Greeting().routeToDestination,
                                        NavOptions(
                                            // Launch the scene as single top
                                            launchSingleTop = true,
                                        ),
                                    )
                                }
                            )
                        }
                    }
                    /*dialog(route = "/greeting/{name}") { backStackEntry ->
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
                    }*/
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