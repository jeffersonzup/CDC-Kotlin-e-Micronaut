package br.com.zup.controller

import br.com.zup.controller.request.AutorRequest
import br.com.zup.controller.response.EnderecoResponse
import br.com.zup.service.EnderecoClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@MicronautTest
internal class AutorControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    internal fun `deve cadastrar um novo autor`() {
        // cenario

        val autorRequest = AutorRequest(
            "Jefferson",
            "jefferson@zup.com.br",
            "Qualquer coisa aqui",
            "07023-040",
            "123"
        )

        val enderecoResponse = EnderecoResponse(
            "Doutor Joao Bueno",
            "Guarulhos",
            "SP"
        )

        Mockito.`when`(enderecoClient.consulta(autorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", autorRequest)

        //acao
        val response = client.toBlocking().exchange(request, Any::class.java)

        // corretude

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}