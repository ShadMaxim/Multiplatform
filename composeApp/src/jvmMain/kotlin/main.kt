import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import org.company.app.App
import org.company.app.di.AppModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(AppModule.appModule)
    }
    application {
        Window(
            title = "Multiplatform App",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
//            resizable = false,
//            transparent = true
        ) {
            window.minimumSize = Dimension(350, 600)
            App()
        }
    }
}