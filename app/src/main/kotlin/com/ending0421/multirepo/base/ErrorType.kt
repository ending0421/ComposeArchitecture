package com.ending0421.multirepo.base

private const val IO_EXCEPTION_CODE = -1
private const val UNHANDLED_BUSINESS_ERROR_CODE = -2
private const val UNKNOWN_EXCEPTION_CODE = -3

open class ErrorType(open val code: Int, open val message: String?)
sealed class CommonErrorType(override val code: Int, override val message: String?) :
    ErrorType(code, message) {
    data object UnknownExceptionError :
        CommonErrorType(UNKNOWN_EXCEPTION_CODE, "UnknownExceptionError")

    data object IOExceptionError : CommonErrorType(IO_EXCEPTION_CODE, "IO Exception")
    data object UnhandledBusinessError :
        CommonErrorType(UNHANDLED_BUSINESS_ERROR_CODE, "Unhandled Business Error")

    data object BadRequestError : CommonErrorType(400, "Bad Request")
    data object UnauthorizedError : CommonErrorType(401, "Unauthorized")
    data object ForbiddenError : CommonErrorType(403, "Forbidden")
    data object NotFoundError : CommonErrorType(404, "Not Found")
    data object MethodNotAllowedError : CommonErrorType(405, "Method Not Allowed")
    data object RequestTimeoutError : CommonErrorType(408, "Request Timeout")
    data object ConflictError : CommonErrorType(409, "Conflict")
    data object InternalServerErrorError : CommonErrorType(500, "Internal Server Error")
    data object NotImplementedError : CommonErrorType(501, "Not Implemented")
    data object BadGatewayError : CommonErrorType(502, "Bad Gateway")
    data object ServiceUnavailableError : CommonErrorType(503, "Service Unavailable")
    data object GatewayTimeoutError : CommonErrorType(504, "Gateway Timeout")

    companion object {
        fun fromCode(code: Int): CommonErrorType? {
            return when (code) {
                BadRequestError.code -> BadRequestError
                UnauthorizedError.code -> UnauthorizedError
                ForbiddenError.code -> ForbiddenError
                NotFoundError.code -> NotFoundError
                MethodNotAllowedError.code -> MethodNotAllowedError
                RequestTimeoutError.code -> RequestTimeoutError
                ConflictError.code -> ConflictError
                InternalServerErrorError.code -> InternalServerErrorError
                NotImplementedError.code -> NotImplementedError
                BadGatewayError.code -> BadGatewayError
                ServiceUnavailableError.code -> ServiceUnavailableError
                GatewayTimeoutError.code -> GatewayTimeoutError
                else -> null
            }
        }
    }
}
