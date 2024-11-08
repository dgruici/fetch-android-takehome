package com.gruici.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruici.myapplication.api.Kryten
import com.gruici.myapplication.api.KrytenInterface
import com.gruici.myapplication.api.data.ItemData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(
    private val kryten: KrytenInterface = Kryten,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var itemViewMap by mutableStateOf<Map<Int, List<ItemData>>>(emptyMap())
        private set

    init {
        updateItemData()
    }

    /**
     * I'm sure we'll talk about this later, but let me get the jump on it now so we have a faster
     * conversation later.
     *
     * I can't rely on the id from the API call. I know that in this case the id appears to match
     * the number in the name field. If I were making an assumption, it's the primary key.
     * Keys can be reused.
     *
     * I am also making an assumption that the ask is to sort by numbers rather than strings.
     * As someone who had to deal with UX, my expectation is numbers ascending. Something to
     * ask our friends in product design for what the expectations are.
     */
    fun updateItemData() {
        viewModelScope.launch(dispatcher) {
            val itemData = kryten.getItemData()

            val sortedMap = itemData.filter {
                !it.name.isNullOrBlank()
            }.sortedWith(
                compareBy<ItemData> {
                    it.listId
                }.thenBy {
                    // this isn't great...
                    it.name!!.split(" ")[1].toInt()
                }
            ).groupBy {
                it.listId
            }

            itemViewMap = sortedMap
        }
    }

}