package com.ending0421.multirepo.base

// 定义常量
private const val UNKNOWN_EXCEPTION_CODE = 1000
private const val TIME_OUT_EXCEPTION_CODE = 1001
private const val IO_EXCEPTION_CODE = 1002
private const val SQLITE_EXCEPTION_CODE = 1003
private const val INVALID_ARGUMENT_ERROR_CODE = 1004
private const val UNHANDLED_BUSINESS_ERROR_CODE = 1005

open class BaseRepoError(open val code: Int, open val message: String?)

sealed class CommonRepoError(override val code: Int, override val message: String?) :
    BaseRepoError(code, message) {
    override fun toString(): String {
        return "${this.javaClass.typeName} code : $code , message : $message"
    }

    object UnknownExceptionRepoError :
        CommonRepoError(UNKNOWN_EXCEPTION_CODE, "UnknownExceptionError")

    object TimeoutExceptionRepoError :
        CommonRepoError(TIME_OUT_EXCEPTION_CODE, "TimeoutExceptionError")

    object IOExceptionRepoError : CommonRepoError(IO_EXCEPTION_CODE, "IO Exception")
    object DatabaseRepoError : CommonRepoError(SQLITE_EXCEPTION_CODE, "SQLite Exception")

    class InvalidArgumentRepoError(message: String) :
        CommonRepoError(INVALID_ARGUMENT_ERROR_CODE, message)

    object UnhandledBusinessRepoError :
        CommonRepoError(UNHANDLED_BUSINESS_ERROR_CODE, "Unhandled Business Error")

    object BadRequestRepoError : CommonRepoError(400, "Bad Request")
    object UnauthorizedRepoError : CommonRepoError(401, "Unauthorized")
    object ForbiddenRepoError : CommonRepoError(403, "Forbidden")
    object NotFoundRepoError : CommonRepoError(404, "Not Found")
    object MethodNotAllowedRepoError : CommonRepoError(405, "Method Not Allowed")
    object RequestTimeoutRepoError : CommonRepoError(408, "Request Timeout")
    object ConflictRepoError : CommonRepoError(409, "Conflict")
    object InternalServerErrorRepoError : CommonRepoError(500, "Internal Server Error")
    object NotImplementedRepoError : CommonRepoError(501, "Not Implemented")
    object BadGatewayRepoError : CommonRepoError(502, "Bad Gateway")
    object ServiceUnavailableRepoError : CommonRepoError(503, "Service Unavailable")
    object GatewayTimeoutRepoError : CommonRepoError(504, "Gateway Timeout")

    companion object {
        fun fromCode(code: Int): CommonRepoError? {
            return when (code) {
                BadRequestRepoError.code -> BadRequestRepoError
                UnauthorizedRepoError.code -> UnauthorizedRepoError
                ForbiddenRepoError.code -> ForbiddenRepoError
                NotFoundRepoError.code -> NotFoundRepoError
                MethodNotAllowedRepoError.code -> MethodNotAllowedRepoError
                RequestTimeoutRepoError.code -> RequestTimeoutRepoError
                ConflictRepoError.code -> ConflictRepoError
                InternalServerErrorRepoError.code -> InternalServerErrorRepoError
                NotImplementedRepoError.code -> NotImplementedRepoError
                BadGatewayRepoError.code -> BadGatewayRepoError
                ServiceUnavailableRepoError.code -> ServiceUnavailableRepoError
                GatewayTimeoutRepoError.code -> GatewayTimeoutRepoError
                else -> null
            }
        }
    }
}