package com.nomanr.lumo.multiplatform.sample.navigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.nomanr.lumo.multiplatform.sample.sample.ComponentId
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


val componentIdNavType = object : NavType<ComponentId>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): ComponentId? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): ComponentId {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: ComponentId) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun serializeAsValue(value: ComponentId): String {
        return Json.encodeToString(value)
    }
}

val componentIdNavTypeMap = mapOf(typeOf<ComponentId>() to componentIdNavType)
