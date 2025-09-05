package org.example.richandmorty.ui.core.components

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems
import org.example.richandmorty.ui.core.Green

@Composable
actual fun <T : Any> LazyRowTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable ((T) -> Unit)
) {
    val lazyState = rememberLazyListState()
    Column {
        LazyRow (state = lazyState){
            items(pagingItems.itemCount) { index ->
                val item = pagingItems[index]
                if (item != null) {
                    itemView(item)
                }
            }
        }
        HorizontalScrollbar(
            adapter = ScrollbarAdapter(lazyState),
            style = LocalScrollbarStyle.current.copy(
                unhoverColor = Green.copy(alpha = 0.4f),
                hoverColor = Green
            )
        )
    }
}