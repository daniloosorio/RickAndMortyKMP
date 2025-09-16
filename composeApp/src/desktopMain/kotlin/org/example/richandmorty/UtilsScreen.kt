package org.example.richandmorty

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.awt.event.MouseListener

@Composable
fun UtilsScreen(){
    MouseListener()
}

@Composable
fun MouseListener(){
    var click by remember {  mutableStateOf("vacio") }
    Column {
        Box(Modifier
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