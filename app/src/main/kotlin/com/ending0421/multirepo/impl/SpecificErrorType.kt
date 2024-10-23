package com.ending0421.multirepo.impl

import com.ending0421.multirepo.base.ErrorType

sealed class SpecificErrorType(override val code: Int, override val message: String?) :
    ErrorType(code, message) {
    data object SpecificBusinessError : SpecificErrorType(10002, "some business error")
    data object UnknownSpecificError : SpecificErrorType(99999, "Unknown Specific Error")

    companion object {
        fun fromCode(code: Int): SpecificErrorType {
            return when (code) {
                SpecificBusinessError.code -> SpecificBusinessError
                else -> UnknownSpecificError
            }
        }
    }
}