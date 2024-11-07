package com.gruici.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gruici.myapplication.api.Kryten
import com.gruici.myapplication.api.KrytenInterface
import com.gruici.myapplication.api.data.ItemData

class ItemViewModel(private val kryten: KrytenInterface = Kryten) : ViewModel() {

    var itemViewMap by mutableStateOf<Map<Int, List<ItemData>>>(emptyMap())
        private set

    init {
        updateItemData()
    }

    fun updateItemData() {
        val itemData = kryten.getItemData()

        val sortedMap = itemData.filter {
            !it.name.isNullOrBlank()
        }.sortedWith(
            compareBy<ItemData> {
                it.listId
            }.thenBy {
                it.name
            }
        ).groupBy {
            it.listId
        }

        itemViewMap = sortedMap
    }

}