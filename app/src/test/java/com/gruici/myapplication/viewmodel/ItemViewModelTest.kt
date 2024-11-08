package com.gruici.myapplication.viewmodel

import com.gruici.myapplication.api.KrytenInterface
import com.gruici.myapplication.api.data.ItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ItemViewModelTest {

    /**
     * PER FT-4
     * List should be sorted by listId, then within the listId's sorted by name
     */
    @Test
    fun didSortItemsByItemIdThenName() {
        val viewModel = ItemViewModel(KrytenTest())
        viewModel.updateItemData()

        assert(viewModel.itemViewMap.isNotEmpty() && viewModel.itemViewMap.size == 3)

        for (key in viewModel.itemViewMap.keys) {
            println("key: $key")
            println(viewModel.itemViewMap[key])
        }

        assert(
            viewModel.itemViewMap.keys == sortedSetOf(
                1,
                3,
                4
            )
        ) // keys in sorted order, sure to be sure

        assert(viewModel.itemViewMap[1]!![0].name == "Item 276")
        assert(viewModel.itemViewMap[1]!![1].name == "Item 684")

        assert(viewModel.itemViewMap[3]!![0].name == "Item 680")

        assert(viewModel.itemViewMap[4]!![0].name == "Item 534")
        assert(viewModel.itemViewMap[4]!![1].name == "Item 808")
    }

    class KrytenTest : KrytenInterface {
        override suspend fun getItemData(): List<ItemData> = listOf(
            ItemData(id=755, listId=2, name=""),
            ItemData(id=203, listId=2, name=""),
            ItemData(id=684, listId=1, name="Item 684"),
            ItemData(id=276, listId=1, name="Item 276"),
            ItemData(id=736, listId=3, name=null),
            ItemData(id=926, listId=4, name=null),
            ItemData(id=808, listId=4, name="Item 808"),
            ItemData(id=599, listId=1, name=null),
            ItemData(id=424, listId=2, name=null),
            ItemData(id=444, listId=1, name=""),
            ItemData(id=809, listId=3, name=null),
            ItemData(id=293, listId=2, name=null),
            ItemData(id=510, listId=2, name=null),
            ItemData(id=680, listId=3, name="Item 680"),
            ItemData(id=231, listId=2, name=null),
            ItemData(id=534, listId=4, name="Item 534"),
            ItemData(id=294, listId=4, name=""),
        )
    }

}