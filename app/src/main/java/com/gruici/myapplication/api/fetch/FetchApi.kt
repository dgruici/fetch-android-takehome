package com.gruici.myapplication.api.fetch

import com.gruici.myapplication.api.data.ItemData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FetchApi(private val httpClient: HttpClient) {

    suspend fun getItemData() : List<ItemData> =
        httpClient.get("https://fetch-hiring.s3.amazonaws.com/hiring.json").body()

}
