package com.ending0421.multirepo.base

sealed class BaseUIError {
    data object NetworkUnavailable : BaseUIError()
    data object SessionExpired : BaseUIError()
    data object GenericError : BaseUIError()
}