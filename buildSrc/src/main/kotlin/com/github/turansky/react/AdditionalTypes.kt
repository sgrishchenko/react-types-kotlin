package com.github.turansky.react

private const val DANGEROUSLY_SET_INNER_HTML = """
interface DangerouslySetInnerHTML {
    __html: string;
}
"""

private const val STYLE_MEDIA = """
interface StyleMedia {
    type: string;
    matchMedium(mediaquery: string): boolean;
}
"""

internal val ADDITIONAL_TYPES = sequenceOf(
    DANGEROUSLY_SET_INNER_HTML,
    STYLE_MEDIA,
).joinToString("")
