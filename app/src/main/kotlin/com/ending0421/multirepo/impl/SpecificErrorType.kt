package com.ending0421.multirepo.impl

import com.ending0421.multirepo.base.BaseRepoError

sealed class SpecificRepoError(
    override val code: Int, override val message: String?
) : BaseRepoError(code, message) {
    data object SpecificBusinessRepoError : SpecificRepoError(10002, "some business error")
    data object UnknownSpecificRepoError : SpecificRepoError(99999, "Unknown Specific Error")

    companion object {
        fun fromCode(code: Int): SpecificRepoError {
            return when (code) {
                SpecificBusinessRepoError.code -> SpecificBusinessRepoError
                else -> UnknownSpecificRepoError
            }
        }
    }
}