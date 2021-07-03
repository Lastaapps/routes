package cz.lastaapps.routes.compose.navigating

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? {

    val functions = T::class.declaredMemberFunctions

    val filtered = functions.filter {
        if (it.name != name) return@filter false
        if (it.parameters.size - 1 != args.size) return@filter false

        return@filter true
    }

    filtered.forEach { function ->
        try {
            function.apply { isAccessible = true }
            return function.call(this, *args)
        } catch (e: IllegalArgumentException) {
        }
    }

    throw IllegalArgumentException("The function $name with ${args.size} parameters not found")
}

inline fun <reified T : Any> T.getPrivateProperty(name: String): Any? =
    T::class
        .memberProperties
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.get(this)