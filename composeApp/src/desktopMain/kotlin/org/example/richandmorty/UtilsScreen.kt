package org.example.richandmorty

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerButtons
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import java.awt.event.KeyListener
import java.awt.event.MouseListener

@Composable
fun UtilsScreen() {
    //MouseListener()
    //KeyListener()
    DragItem()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragItem() {
    var currentOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    Box(
        Modifier
            .offset { IntOffset(currentOffset.x.toInt(), currentOffset.y.toInt()) }
            .size(100.dp)
            .background(Color.Blue)
            .onDrag(
                matcher = PointerMatcher.mouse(PointerButton.Primary),
                onDragStart = { print("drag started") },
                onDragEnd = { print("drag ended") },
                onDrag = { currentOffset += it }
            )
    ) {
        Text("Drag me")
    }

}

@Composable
fun KeyListener() {
    var texts by remember { mutableStateOf("") }
    Box(modifier = Modifier.onPreviewKeyEvent {
        if (it.key == Key.Spacebar) {
            texts = "danilo"
            true
        } else {
            false
        }
    }) {
        TextField(value = texts, onValueChange = { texts = it })
    }
}

@Composable
fun MouseListener() {
    var click by remember { mutableStateOf("vacio") }
    Column {
        Box(
            Modifier
            .size(300.dp)
            .background(Color.Red)
            .combinedClickable(
                onClick = { click = "click" },
                onDoubleClick = { click = "doble click" },
                onLongClick = { click = "long click" }
            )
        )
        Text("tipo de click: $click")
    }

}