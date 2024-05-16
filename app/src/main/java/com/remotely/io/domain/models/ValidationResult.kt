package com.remotely.io.domain.models

import com.remotely.io.components.UiText

sealed interface ValidationResult {
    data object Success : ValidationResult
    data class Error(val errorMessage: UiText) : ValidationResult
}
