package br.com.zup.repository

import br.com.zup.model.Autor
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long>{

    @Query("SELECT a FROM Autor a WHERE a.email = :email")
    fun buscaPorEmail(email: String) : Optional<Autor>

    //Equivalente
    //fun findByEmail(email: String) : Optional<Autor>

}

