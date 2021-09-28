package br.com.zup.carros

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import jakarta.inject.Singleton
import java.time.LocalDate
import java.time.Period
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FIELD

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [MaiorIdadeValidator::class])
annotation class MaiorIdade (val message: String = "Precisa ser maior de idade")

@Singleton
class MaiorIdadeValidator : ConstraintValidator<MaiorIdade, LocalDate> {

    override fun isValid(
        value: LocalDate?,
        annotationMetadata: AnnotationValue<MaiorIdade>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null){
            return true
        }

        val dataNasc: LocalDate = value
        val dataAtual: LocalDate = LocalDate.now()
        val period = Period.between(dataNasc, dataAtual)

        if (period.years < 18 ){
            return false
        }

        return true
    }

}
