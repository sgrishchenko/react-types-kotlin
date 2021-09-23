package com.github.turansky.csstype

private const val GRID_LENGTH = "GridLength"

internal fun GridLength(): ConversionResult {
    val declarations = sequenceOf(
        "sealed external interface $GRID_LENGTH",

        """
            inline fun $GRID_LENGTH(value: String): $GRID_LENGTH =
                value.unsafeCast<$GRID_LENGTH>()
        """.trimIndent(),

        unitsExtension(GRID_LENGTH, "fr", "fr"),
    )

    return ConversionResult(GRID_LENGTH, declarations.joinToString("\n\n"))
}