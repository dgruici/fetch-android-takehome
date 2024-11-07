package com.gruici.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gruici.myapplication.ui.theme.MyApplicationTheme
import com.gruici.myapplication.viewmodel.ItemViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ItemDataText()
            }
        }
    }
}

@Composable
fun ItemDataText(viewModel: ItemViewModel = viewModel()) {
    Text(
        text = viewModel.itemViewMap.toString()
    )
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyApplicationTheme {
        ItemDataText()
    }
}