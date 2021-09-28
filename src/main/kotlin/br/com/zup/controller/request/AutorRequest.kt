package br.com.zup.controller.request

import br.com.zup.controller.response.EnderecoResponse
import br.com.zup.model.Autor
import br.com.zup.model.Endereco
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String

) {
    fun convertToAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse, numero, cep)
        return Autor(nome, email, descricao, endereco)
    }
}