package br.com.zup.controller

import br.com.zup.controller.request.AutorRequest
import br.com.zup.controller.response.DetalheAutorResponse
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class AtualizaAutorController (val autorRepository: AutorRepository){

    @Put("/{id}")
    fun atualiza(@PathVariable id: Long, @Body @Valid autorRequest: AutorRequest): HttpResponse<Any>{
        val existsId = autorRepository.findById(id)

        if (!existsId.isPresent){
            return HttpResponse.notFound()
        }

        val autor = existsId.get()
        autor.nome = autorRequest.nome
        autor.email = autorRequest.email
        autor.descricao = autorRequest.descricao

        autorRepository.update(autor)

        return HttpResponse.ok(DetalheAutorResponse(autor))
    }
}