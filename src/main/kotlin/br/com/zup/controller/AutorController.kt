package br.com.zup.controller

import br.com.zup.controller.request.AutorRequest
import br.com.zup.controller.response.DetalheAutorResponse
import br.com.zup.repository.AutorRepository
import br.com.zup.service.EnderecoClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: AutorRequest): HttpResponse<Any> {
        val enderecoResponse = enderecoClient.consulta(request.cep)

        val autor = request.convertToAutor(enderecoResponse.body()!!)

        val autorSalvo = autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.ok(DetalheAutorResponse(autorSalvo))
    }

}