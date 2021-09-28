package br.com.zup.controller.response

import br.com.zup.model.Autor

data class DetalheAutorResponse(val nome: String, val email: String, val descricao: String) {

    constructor(autor: Autor): this(autor.nome, autor.email, autor.descricao)

}
