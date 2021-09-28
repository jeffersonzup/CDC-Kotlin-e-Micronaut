package br.com.zup.carros

import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.*

@Introspected
data class ClienteRequest(

    @field:NotBlank @field:Size(max=120)
    val nome: String,
    @field:Email
    val email: String,
    @field:NotNull @field:Past @field:MaiorIdade
    @JsonFormat(pattern = "dd/MM/yyyy")
    val dataDeNascimento: LocalDate,
    // outros atributos
)
