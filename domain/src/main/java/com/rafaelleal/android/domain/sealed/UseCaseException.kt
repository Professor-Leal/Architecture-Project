package com.rafaelleal.android.domain.sealed

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {

    class AddressException(cause: Throwable): UseCaseException(cause)

    class UnknownException(cause: Throwable) : UseCaseException(cause)

    companion object {
        fun createFromThrowable(throwable: Throwable):
                UseCaseException {
            return if (throwable is UseCaseException)
                throwable else UnknownException(throwable)
        }
    } // -- companion object  -- //

}


