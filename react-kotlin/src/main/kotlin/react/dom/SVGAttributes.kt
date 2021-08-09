// Automatically generated - do not modify!

package react.dom

external interface SVGAttributes<T> : AriaAttributes, DOMAttributes<T> {
    // Attributes which also defined in HTMLAttributes
// See comment in SVGDOMPropertyConfig.js
    var className: String
    var color: String
    var height: Double
    var id: String
    var lang: String
    var max: Double
    var media: String
    var method: String
    var min: Double
    var name: String
    var style: dynamic // CSSProperties
    var target: String
    var type: String
    var width: Double

    // Other HTML properties supported by SVG elements in browsers
    var role: String
    var tabIndex: Int
    var crossOrigin: String // "anonymous" | "use-credentials" | ""

    // SVG Specific attributes
    var accentHeight: Double
    var accumulate: String // "none" | "sum"
    var additive: String // "replace" | "sum"
    var alignmentBaseline: String // "auto" | "baseline" | "before-edge" | "text-before-edge" | "middle" | "central" | "after-edge" |"text-after-edge" | "ideographic" | "alphabetic" | "hanging" | "mathematical" | "inherit"
    var allowReorder: String // "no" | "yes"
    var alphabetic: Double
    var amplitude: Double
    var arabicForm: String // "initial" | "medial" | "terminal" | "isolated"
    var ascent: Double
    var attributeName: String
    var attributeType: String
    var autoReverse: Boolean
    var azimuth: Double
    var baseFrequency: Double
    var baselineShift: String
    var baseProfile: String
    var bbox: String
    var begin: String
    var bias: Double
    var by: Double
    var calcMode: String
    var capHeight: Double
    var clip: String
    var clipPath: String
    var clipPathUnits: String
    var clipRule: String
    var colorInterpolation: String
    var colorInterpolationFilters: String // "auto" | "sRGB" | "linearRGB" | "inherit"
    var colorProfile: String
    var colorRendering: String
    var contentScriptType: String
    var contentStyleType: String
    var cursor: String
    var cx: Double
    var cy: Double
    var d: String
    var decelerate: String
    var descent: Double
    var diffuseConstant: Double
    var direction: String
    var display: String
    var divisor: Double
    var dominantBaseline: String
    var dur: String
    var dx: Double
    var dy: Double
    var edgeMode: String
    var elevation: Double
    var enableBackground: String
    var end: String
    var exponent: Double
    var externalResourcesRequired: Boolean
    var fill: String
    var fillOpacity: String
    var fillRule: String // "nonzero" | "evenodd" | "inherit"
    var filter: String
    var filterRes: Double
    var filterUnits: String
    var floodColor: String
    var floodOpacity: String
    var focusable: dynamic // Booleanish | "auto"
    var fontFamily: String
    var fontSize: Double
    var fontSizeAdjust: String
    var fontStretch: String
    var fontStyle: String
    var fontVariant: String
    var fontWeight: String
    var format: String
    var from: String
    var fx: Double
    var fy: Double
    var g1: String
    var g2: String
    var glyphName: String
    var glyphOrientationHorizontal: String
    var glyphOrientationVertical: String
    var glyphRef: String
    var gradientTransform: String
    var gradientUnits: String
    var hanging: Double
    var horizAdvX: Double
    var horizOriginX: Double
    var href: String
    var ideographic: Double
    var imageRendering: String
    var in2: String
    var `in`: String
    var intercept: Double
    var k1: Double
    var k2: Double
    var k3: Double
    var k4: Double
    var k: Double
    var kernelMatrix: String
    var kernelUnitLength: String
    var kerning: String
    var keyPoints: String
    var keySplines: String
    var keyTimes: String
    var lengthAdjust: String
    var letterSpacing: String
    var lightingColor: String
    var limitingConeAngle: Double
    var local: String
    var markerEnd: String
    var markerHeight: Double
    var markerMid: String
    var markerStart: String
    var markerUnits: String
    var markerWidth: Double
    var mask: String
    var maskContentUnits: String
    var maskUnits: String
    var mathematical: Double
    var mode: String
    var numOctaves: Int
    var offset: String
    var opacity: Double
    var operator: String
    var order: Int
    var orient: String
    var orientation: String
    var origin: String
    var overflow: String
    var overlinePosition: Double
    var overlineThickness: Double
    var paintOrder: String
    var panose1: String
    var path: String
    var pathLength: Double
    var patternContentUnits: String
    var patternTransform: String
    var patternUnits: String
    var pointerEvents: String
    var points: String
    var pointsAtX: Double
    var pointsAtY: Double
    var pointsAtZ: Double
    var preserveAlpha: Boolean
    var preserveAspectRatio: String
    var primitiveUnits: String
    var r: Double
    var radius: Double
    var refX: Double
    var refY: Double
    var renderingIntent: Int
    var repeatCount: Double
    var repeatDur: String
    var requiredExtensions: String
    var requiredFeatures: String
    var restart: String
    var result: String
    var rotate: String
    var rx: Double
    var ry: Double
    var scale: Double
    var seed: Int
    var shapeRendering: String
    var slope: Double
    var spacing: String
    var specularConstant: Double
    var specularExponent: Double
    var speed: String
    var spreadMethod: String
    var startOffset: String
    var stdDeviation: String
    var stemh: Double
    var stemv: Double
    var stitchTiles: String
    var stopColor: String
    var stopOpacity: Double
    var strikethroughPosition: Double
    var strikethroughThickness: Double
    var string: String
    var stroke: String
    var strokeDasharray: dynamic // string | number
    var strokeDashoffset: dynamic // string | number
    var strokeLinecap: String // "butt" | "round" | "square" | "inherit"
    var strokeLinejoin: String // "miter" | "round" | "bevel" | "inherit"
    var strokeMiterlimit: Double
    var strokeOpacity: String
    var strokeWidth: Double
    var surfaceScale: Double
    var systemLanguage: String
    var tableValues: String
    var targetX: Double
    var targetY: Double
    var textAnchor: String
    var textDecoration: String
    var textLength: String
    var textRendering: String
    var to: String
    var transform: String
    var u1: String
    var u2: String
    var underlinePosition: Double
    var underlineThickness: Double
    var unicode: String
    var unicodeBidi: String
    var unicodeRange: String
    var unitsPerEm: Double
    var vAlphabetic: Double
    var values: String
    var vectorEffect: String
    var version: String
    var vertAdvY: Double
    var vertOriginX: Double
    var vertOriginY: Double
    var vHanging: Double
    var vIdeographic: Double
    var viewBox: String
    var viewTarget: String
    var visibility: String
    var vMathematical: Double
    var widths: String
    var wordSpacing: String
    var writingMode: String
    var x1: Double
    var x2: Double
    var x: Double
    var xChannelSelector: String
    var xHeight: Double
    var xlinkActuate: String
    var xlinkArcrole: String
    var xlinkHref: String
    var xlinkRole: String
    var xlinkShow: String
    var xlinkTitle: String
    var xlinkType: String
    var xmlBase: String
    var xmlLang: String
    var xmlns: String
    var xmlnsXlink: String
    var xmlSpace: String
    var y1: Double
    var y2: Double
    var y: Double
    var yChannelSelector: String
    var z: Double
    var zoomAndPan: String
}
