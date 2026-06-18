package com.naul.needle.legacy

import kotlin.jvm.java

@Suppress("UNCHECKED_CAST")
class LazyFactoryProvider(private val parent: InstanceProvider? = null) : InstanceProvider {
    private val instancesCached = mutableMapOf<Class<*>, Any>()
    val factoriesCached = mutableMapOf<Class<*>, (InstanceProvider) -> Any>()

    override fun <T> get(clazz: Class<T>): T {
        instancesCached[clazz]?.let { return it as T }

        factoriesCached[clazz]?.let { factory ->
            val instance = factory(this)
            instancesCached[clazz] = instance
            return instance as T
        }
        if (parent != null) {
            return parent.get()
        }

        throw IllegalArgumentException("No instance found for type: ${clazz.name}")
    }

    inline fun <reified T : Any> register(noinline factory: (InstanceProvider) -> T) {
        factoriesCached[T::class.java] = factory
    }
}