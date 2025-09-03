package org.example.richandmorty

import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.richandmorty.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and morty app"
    ){
      // Text("holiiii")
        initKoin()
        App()
    }
}