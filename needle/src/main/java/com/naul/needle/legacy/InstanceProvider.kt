package com.naul.needle.legacy

import kotlin.jvm.java

interface InstanceProvider {
    fun <T> get(clazz: Class<T>): T
}

inline fun <reified T : Any> InstanceProvider.get(): T {
    return this.get(T::class.java)
}