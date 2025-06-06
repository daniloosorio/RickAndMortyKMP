package org.example.richandmorty

import androidx.compose.ui.window.ComposeUIViewController
import org.example.richandmorty.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {initKoin()}) { App() }