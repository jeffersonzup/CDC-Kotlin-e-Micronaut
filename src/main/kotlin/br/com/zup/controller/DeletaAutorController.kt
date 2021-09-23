package br.com.zup.controller

import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete("/{id}")
    fun delete(@PathVariable id: Long) : HttpResponse<Any> {
        val existsId = autorRepository.findById(id)
        if(existsId.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = existsId.get()
        autorRepository.delete(autor)

        return HttpResponse.noContent()
    }

}