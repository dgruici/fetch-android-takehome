package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData

interface KrytenInterface {

    fun getItemData() : List<ItemData>

}