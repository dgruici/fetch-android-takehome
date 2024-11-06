package com.gruici.myapplication.api.fetch

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FetchApiTest {

    @Test
    fun isKtorWorking() {
        val httpClient = HttpClient(mockEngine(exampleResponse)) {
            install(ContentNegotiation) {
                json()
            }
        }

        runBlocking {
            val response = FetchApi(httpClient).getItemData()
            println(response)
            assert(response.isNotEmpty() && response.size == 7)
        }
    }

    private fun mockEngine(content: String) = MockEngine { _ ->
        respond(
            content = ByteReadChannel(content),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

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