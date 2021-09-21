package com.github.turansky.csstype

internal const val AUTO_LENGTH_PROPERTY = "AutoLengthProperty"

internal fun AutoLengthProperty(): ConversionResult {
    val body = """
        sealed external interface $AUTO_LENGTH_PROPERTY
    """.trimIndent()

    return ConversionResult(AUTO_LENGTH_PROPERTY, body)
}
