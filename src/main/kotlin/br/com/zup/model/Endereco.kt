package br.com.zup.model

import br.com.zup.controller.response.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(
    enderecoResponse: EnderecoResponse,
    val numero: String,
    val cep: String
) {
    val logradouro = enderecoResponse.logradouro
    val localidade = enderecoResponse.localidade
    val uf = enderecoResponse.uf
}
