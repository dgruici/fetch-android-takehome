package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData
import com.gruici.myapplication.api.fetch.FetchApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

/**
 * I always end up naming this layer Kryten because, like their namesake, they live to serve
 * I am also lazy I know. Objects are a singleton so I can ensure it's the same one each time.
 */
object Kryten : KrytenInterface {

    private val httpClient: HttpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    override suspend fun getItemData() : List<ItemData> =
        FetchApi(httpClient).getItemData()
}
