package org.example.richandmorty.ui.core.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    val state = rememberWebViewState(url = url)
    state.webSettings.apply {
        isJavaScriptEnabled = true
    }
    TooltipArea(
        tooltip = {
            Text(
                "preparado para ver R%M",
                color = Color.Black,
                modifier = Modifier.background(Color.Green, shape = RoundedCornerShape(4.dp))
            )
        },
        delayMillis = 1500,
        tooltipPlacement = TooltipPlacement.CursorPoint(
            alignment = Alignment.TopStart,
            offset = DpOffset((-16).dp,56.dp)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            WebView(state, Modifier.height(315.dp).width(560.dp))
        }
    }
}