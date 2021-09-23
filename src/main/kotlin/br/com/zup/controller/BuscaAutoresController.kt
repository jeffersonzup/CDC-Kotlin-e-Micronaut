package br.com.zup.controller

import br.com.zup.controller.response.DetalheAutorResponse
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(): HttpResponse<List<DetalheAutorResponse>> {
        val autores = autorRepository.findAll()

        val resposta = autores.map { autor -> DetalheAutorResponse(autor) }

        return HttpResponse.ok(resposta)

    }
}