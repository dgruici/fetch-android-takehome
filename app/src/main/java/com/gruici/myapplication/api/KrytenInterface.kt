package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData

interface KrytenInterface {

    suspend fun getItemData(): List<ItemData>

}
