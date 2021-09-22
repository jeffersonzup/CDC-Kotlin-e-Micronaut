package br.com.zup.controller

import br.com.zup.controller.request.AutorRequest
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(var autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: AutorRequest) : HttpResponse<Any>{
        val autor = request.convertToAutor()
        val autorSalvo = autorRepository.save(autor)
        return HttpResponse.created(autorSalvo)
    }

}