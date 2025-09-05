package org.example.richandmorty.ui.core.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
actual fun <T : Any> LazyRowTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable ((T) -> Unit)
) {
    LazyRow {
        items(pagingItems.itemCount) { index ->
            val item = pagingItems[index]
            if (item != null) {
                itemView(item)
            }
        }
    }
}