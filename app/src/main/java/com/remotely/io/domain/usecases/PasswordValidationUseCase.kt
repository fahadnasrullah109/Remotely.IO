package com.remotely.io.domain.usecases
import com.remotely.io.components.UiText
import com.remotely.io.domain.models.ValidationResult
import com.remotely.io.utils.AppConstant
import javax.inject.Inject

class PasswordValidationUseCase @Inject constructor() {

    fun execute(field: String, emptyErrorString: String, errorString: String): ValidationResult =
        when {
            isBlank(field) -> ValidationResult.Error(errorMessage = UiText.Dynamic(emptyErrorString))
            isValidLength(field).not() -> ValidationResult.Error(
                errorMessage = UiText.Dynamic(
                    errorString
                )
            )

            else -> ValidationResult.Success
        }

    private fun isBlank(password: String) = password.isBlank()

    private fun isValidLength(password: String) = password.length >= AppConstant.validPasswordLength
}