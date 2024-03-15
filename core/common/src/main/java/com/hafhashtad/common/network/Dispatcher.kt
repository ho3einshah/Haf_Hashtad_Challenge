package com.hafhashtad.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val hafHashtadDispatcher: HafHashtadDispatchers)

enum class HafHashtadDispatchers {
    IO,
}