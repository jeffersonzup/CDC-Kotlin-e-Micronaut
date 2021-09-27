package br.com.zup.controller

import br.com.zup.controller.response.DetalheAutorResponse
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores = autorRepository.findAll()

            val resposta = autores.map { autor -> DetalheAutorResponse(autor) }

            return HttpResponse.ok(resposta)
        }

        val existsEmail = autorRepository.findByEmail(email)

        if (!existsEmail.isPresent) {
            return HttpResponse.notFound()
        }

        val autor = existsEmail.get()
        return HttpResponse.ok(DetalheAutorResponse(autor))

    }

}