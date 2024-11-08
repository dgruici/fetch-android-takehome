package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData
import com.gruici.myapplication.api.fetch.FetchApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

/**
 * I always end up naming this layer Kryten because, like their namesake, they live to serve
 * I am also lazy I know. Objects are basically a singleton.
 */
object Kryten : KrytenInterface {

    private val httpClient: HttpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

//    override suspend fun getItemData() : List<ItemData> =
//        FetchApi(httpClient).getItemData()

    override suspend fun getItemData() : List<ItemData> =
        Json.decodeFromString(longResponse)
}