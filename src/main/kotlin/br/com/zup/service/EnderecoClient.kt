package br.com.zup.service

import br.com.zup.controller.response.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws/")
interface EnderecoClient {

    @Get("{cep}/json/")
    @Consumes(MediaType.APPLICATION_XML)
    fun consulta(@QueryValue cep: String) : HttpResponse<EnderecoResponse>
}

