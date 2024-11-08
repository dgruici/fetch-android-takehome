package com.gruici.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gruici.myapplication.ui.theme.MyApplicationTheme
import com.gruici.myapplication.viewmodel.ItemViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gruici.myapplication.api.data.ItemData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ItemList()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemList(viewModel: ItemViewModel = viewModel()) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        viewModel.itemViewMap.forEach { (itemId, itemData) ->
            stickyHeader {
                ItemDataHeader(itemId)
            }

            items(itemData) { item ->
                ItemDataListItem(item)
            }
        }
    }
}

@Composable
fun ItemDataHeader(itemId: Int) {
    Text(
        text = "listId: $itemId",
        color = Color.White,
        modifier = Modifier.background(Color.Black)
            .fillMaxWidth()
    )
}

/**
 * This can go two ways. A double bang is not always desired.
 * One, no nulls is part of the criteria, so it makes sense to crash to show that a null got through
 * On the other, we should always fail as gracefully as possible.
 * We'd want to ask product design what the desired outcome is here.
 * I'm reusing my ItemData class so it is nullable. Mapping to a non-null class would
 * remove the double bang.
 */
@Composable
fun ItemDataListItem(item: ItemData) {
    Text(
        text = item.name!!,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyApplicationTheme {
        ItemList()
    }
}
