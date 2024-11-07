package com.gruici.myapplication.api

import com.gruici.myapplication.api.data.ItemData
import com.gruici.myapplication.api.fetch.FetchApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
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

    // suspend fun getItemData() : List<ItemData> = FetchApi(httpClient).getItemData()
    // this is just here to show that I would do this while building a feature
    // agree on a response structure, make a mock response, work in parallel with our backend friends
    override fun getItemData() : List<ItemData> = Json.decodeFromString(exampleResponse)

    private val exampleResponse = "[\n" +
            "{\"id\": 755, \"listId\": 2, \"name\": \"\"},\n" +
            "{\"id\": 203, \"listId\": 2, \"name\": \"\"},\n" +
            "{\"id\": 684, \"listId\": 1, \"name\": \"Item 684\"},\n" +
            "{\"id\": 276, \"listId\": 1, \"name\": \"Item 276\"},\n" +
            "{\"id\": 736, \"listId\": 3, \"name\": null},\n" +
            "{\"id\": 926, \"listId\": 4, \"name\": null},\n" +
            "{\"id\": 808, \"listId\": 4, \"name\": \"Item 808\"}" +
            "]"

}