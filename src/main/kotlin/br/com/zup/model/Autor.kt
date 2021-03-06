package br.com.zup.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Autor (
    var nome: String,
    var email: String,
    var descricao: String,
    val endereco: Endereco
    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    var criadoEm: LocalDateTime = LocalDateTime.now()
}
