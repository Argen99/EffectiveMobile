package com.example.core

sealed class Either<out A, out B> {
    class Error<out A>(val value: A) : Either<A, Nothing>()
    class Success<out B>(val value: B) : Either<Nothing, B>()
}