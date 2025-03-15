package com.ending0421.multirepo.base

abstract class BaseUseCase<UIError : BaseUIError, RepoError : BaseRepoError> {
    abstract fun convertToUIError(repoError: RepoError): UIError
}