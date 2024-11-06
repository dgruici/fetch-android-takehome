package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData
import com.gruici.myapplication.api.fetch.FetchApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

/**
 * I always end up naming this layer Kryten because, like their namesake, they live to serve
 */
class Kryten {

    private val httpClient: HttpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    suspend fun getItemData() : List<ItemData> = FetchApi(httpClient).getItemData()

}