package org.example.richandmorty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Window
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.isTraySupported
import androidx.compose.ui.window.rememberTrayState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import dev.datlag.kcef.KCEF
import dev.datlag.kcef.KCEFBrowser
import org.example.richandmorty.di.initKoin
import org.example.richandmorty.ui.core.BackgroundPrimaryColor
import org.example.richandmorty.ui.core.Green
import org.jetbrains.compose.resources.painterResource
import richandmorty.composeapp.generated.resources.Res
import richandmorty.composeapp.generated.resources.images
import java.awt.TrayIcon
import java.io.File
import kotlin.math.max

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and morty app"
    ) {
    /*    var restartRequired by remember { mutableStateOf(false) }
        var downloading by remember { mutableStateOf(0F) }
        var initialize by remember { mutableStateOf(false) }
        var trayState = rememberTrayState()

        if(isTraySupported) {
            Tray(
                state = trayState,
                icon = painterResource(Res.drawable.images),
                menu = {
                    Item("Exit", onClick = ::exitApplication)
                }
            )
        }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                KCEF.init(builder = {
                    installDir(File("kcef-bundle"))
                    progress {
                        onDownloading {
                            downloading = max(it, 0F)
                        }
                        onInitialized {
                            initialize = true
                        }
                    }
                    settings {
                        cachePath = File("cache").absolutePath
                    }
                }, onError = {
                    it?.printStackTrace()
                }, onRestartRequired = {
                    restartRequired = true
                })
            }
        }

        if (restartRequired) {
            Text("Restart require")
        } else {
            if (initialize) {*/
                initKoin()
                App()
    /*            //UtilsScreen()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.background(BackgroundPrimaryColor).fillMaxSize()
                ) {
                    CircularProgressIndicator(color = Green)
                    Text("Initializing")
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                KCEF.disposeBlocking()
            }
        }*/

    }
}