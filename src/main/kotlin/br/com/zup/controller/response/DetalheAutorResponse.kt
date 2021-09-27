package br.com.zup.controller.response

import br.com.zup.model.Autor

class DetalheAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    val logradouro = autor.endereco.logradouro
    val numero = autor.endereco.numero
    val localidade = autor.endereco.localidade
    val uf = autor.endereco.uf

}
