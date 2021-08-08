package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

// TODO: generate
private val HTML_ATTRIBUTE_REFERRER_POLICY = ConversionResult(
    name = "HTMLAttributeReferrerPolicy",
    body = "typealias HTMLAttributeReferrerPolicy = String",
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .replace("HTMLTableHeaderCellElement", "HTMLTableCellElement")
        .replace("HTMLTableDataCellElement", "HTMLTableCellElement")
        .replace("HTMLWebViewElement", "HTMLElement")
        .replace("\r\n", "\n")

    val reactContent = content
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent()

    return reactContent.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .mapNotNull {
            convertInterface(
                name = it.substringBefore(" ")
                    .substringBefore("<"),
                source = it,
            )
        }
        .plus(convertNativeEvents(content))
        .plus(convertEventHandlers(reactContent))
        .plus(HTML_ATTRIBUTE_REFERRER_POLICY)
}

private val NATIVE_EVENT_REPLACEMENT = mapOf(
    "AnimationEvent" to "Event",
    "ClipboardEvent" to "Event",
    "DragEvent" to "MouseEvent",
    "TouchEvent" to "MouseEvent",
    "PointerEvent" to "MouseEvent",
    "TransitionEvent" to "Event",
)

private fun convertNativeEvents(
    source: String,
): ConversionResult {
    val body = source.splitToSequence("\n")
        .filter { it.startsWith("type Native") }
        .joinToString("\n\n") { line ->
            val name = line.removePrefix("type ")
                .substringBefore(" = ")

            val alias = line.substringAfter(" = ")
                .removeSuffix(";")
                .let { NATIVE_EVENT_REPLACEMENT[it] ?: it }

            "typealias $name = org.w3c.dom.events.$alias"
        }


    return ConversionResult("NativeEvents", body)
}

private const val EVENT_HANDLER = "typealias EventHandler<E> = (event: E) -> Unit"

private fun convertEventHandlers(
    source: String,
): ConversionResult {
    val handlers = source.splitToSequence("\n")
        .filter { it.startsWith("type ") && " = EventHandler<" in it }
        .joinToString("\n\n") { line ->
            line.replaceFirst("type ", "typealias ")
                .removeSuffix(";")
                .replace("<T = Element>", "<T>")
                .replace("SyntheticEvent<T>", "SyntheticEvent<T, *>")
                .replace("MouseEvent<T>", "MouseEvent<T, *>")
                .replace("UIEvent<T>", "UIEvent<T, *>")
        }

    val body = EVENT_HANDLER + "\n\n" + handlers

    return ConversionResult("EventHandlers", body)
}

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult? =
    when {
        name.endsWith("Event") -> convertEventInterface(name, source)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source)
        name == "ReactHTML" -> convertIntrinsicTypes(source)
        else -> null
    }

private const val DEFAULT_EVENT_IMPORTS = """
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
"""

private fun convertEventInterface(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source.substringBefore(" {")
        .replaceFirst(" extends ", " : ")
        .replace(" = ", " : ")
        .replace(": object", ": Any")
        .replace(": any", ": Any")
        .replace("EventTarget & T", "T")
        .replace("SyntheticEvent<T>", "SyntheticEvent<T, Event>")

    var members = convertMembers(source, true)
    when (name) {
        "ChangeEvent",
        "FocusEvent",
        "InvalidEvent",
        -> members = members.replaceFirst("val target:", "override val target:")
    }

    val body = DEFAULT_EVENT_IMPORTS.removePrefix("\n") +
            "\nexternal interface $declaration {\n" +
            members +
            "\n}\n"

    return ConversionResult(name, body)
}

private fun convertAttributesInterface(
    name: String,
    source: String,
): ConversionResult? {
    when (name) {
        "Attributes",
        "RefAttributes",
        "ClassAttributes",
        -> return null
    }

    var declaration = source.substringBefore(" {")
        .replace(" extends ", " : ")

    when (name) {
        "DOMAttributes",
        -> declaration += ": react.PropsWithChildren"

        "DetailsHTMLAttributes",
        "InputHTMLAttributes",
        "SelectHTMLAttributes",
        "TextareaHTMLAttributes",
        -> declaration = declaration.replaceFirst("<T>", "<T: Element>")
    }

    val content = when (name) {
        "DOMAttributes" -> source.substringAfter("};\n\n")
        else -> source
    }

    var members = when (name) {
        // TODO: support
        "AriaAttributes" -> ""
        else -> convertMembers(content, false)
    }

    members = when (name) {
        "AllHTMLAttributes",
        "InputHTMLAttributes",
        "TextareaHTMLAttributes",
        -> members.replaceFirst("var placeholder: ", "override var placeholder: ")

        "VideoHTMLAttributes",
        -> members.replaceFirst("var playsInline: ", "override var playsInline: ")

        else -> members
    }

    val body = "import org.w3c.dom.Element\n\n" +
            "external interface $declaration {\n" +
            members +
            "\n}\n"

    return ConversionResult(name, body)
}

private fun convertMembers(
    source: String,
    final: Boolean,
): String {
    val content = source
        .substringAfter("{\n", "")
        .trimIndent()
        .replace("\n\n", "\n")

    if (content.isEmpty())
        return ""

    return content.removeSuffix(";")
        .splitToSequence(";\n")
        .joinToString("\n") {
            convertMember(it, final)
        }
}

private fun convertMember(
    source: String,
    final: Boolean,
): String {
    if ("; // " in source) {
        if ("\n// " in source) {
            return source.splitToSequence("\n// ")
                .mapIndexed { index, item -> if (index == 0) item else "// " + item }
                .map { convertMember(it, final) }
                .joinToString("\n")
        } else if (!source.startsWith("// ") && source.count { it == '\n' } == 1) {
            return source.splitToSequence("\n")
                .map { convertMember(it, final) }
                .joinToString("\n")
        }
    }

    if ("\n" in source) {
        if (!source.startsWith("/*") && !source.startsWith("//"))
            return convertMember(source.replace("\n", ""), final)

        var comment = source.substringBeforeLast("\n")
        if (comment == "/** @deprecated */")
            comment = """@Deprecated("Will be removed soon!")"""

        return comment + "\n" + convertMember(source.substringAfterLast("\n"), final)
    }

    return if ("(" in source) {
        convertMethod(source)
    } else {
        convertProperty(source, final)
    }
}

private fun convertProperty(
    source: String,
    final: Boolean,
): String {
    val name = source.substringBefore(": ")
        .removeSuffix("?")
    val id = when (name) {
        "is", "as", "typeof", "in" -> "`$name`"
        else -> name
    }

    val sourceType = source.substringAfter(": ")
        .replace("EventTarget & T", "T")
    val type = kotlinType(sourceType, name)
    val keyword = if (final) "val" else "var"
    return "$keyword $id: $type"
}

private fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")

    val params = source.substringAfter("(")
        .substringBefore("): ")
    val parameters = if (params.isNotEmpty()) {
        params.splitToSequence(", ")
            .joinToString(", ") {
                val (pname, ptype) = it.split(": ")
                "$pname: ${kotlinType(ptype, pname)}"
            }
    } else ""

    val returnType = kotlinType(source.substringAfter("): "), name)
    val returns = if (returnType != UNIT) ": $returnType" else ""

    return "fun $name($parameters)$returns"
}

private fun convertIntrinsicTypes(
    source: String,
): ConversionResult {
    val body = source.substringAfter("{\n")
        .trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertIntrinsicType(it) }
        .joinToString("\n\n")

    return ConversionResult("IntrinsicTypes", "import react.IntrinsicType\n\n" + body)
}

private fun convertIntrinsicType(
    source: String,
): String {
    val name = source.substringBefore(": ")
        .removeSurrounding("\"")

    val propsType = source.substringAfter(": DetailedHTMLFactory<")
        .substringBefore(",")
        .replaceFirst("<", "<org.w3c.dom.")
    val type = "IntrinsicType<$propsType>"

    val id = when (name) {
        "object", "var" -> "`$name`"
        else -> name
    }

    return """
        inline val $id: $type
            get() = "$name".unsafeCast<$type>()
    """.trimIndent()
}
