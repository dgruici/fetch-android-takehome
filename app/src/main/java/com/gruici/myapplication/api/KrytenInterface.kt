package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData
import kotlinx.coroutines.CoroutineDispatcher

interface KrytenInterface {

    suspend fun getItemData(): List<ItemData>

}