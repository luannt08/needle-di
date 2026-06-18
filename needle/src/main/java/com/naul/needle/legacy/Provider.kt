package com.naul.needle.legacy

abstract class RootContainer(registry: LazyFactoryProvider.() -> Unit) :
    InstanceProvider by LazyFactoryProvider(null).apply(registry) {
}

abstract class SessionContainer(parent: InstanceProvider?, registry: LazyFactoryProvider.() -> Unit) :
    InstanceProvider by LazyFactoryProvider(parent).apply(registry)