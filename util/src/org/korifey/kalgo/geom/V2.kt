package org.korifey.kalgo.geom

data class V2(val x: Double, val y: Double) {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    companion object {
        val O = V2(0, 0)
        val X = V2(1, 0)
        val Y = V2(0, 1)
    }
}

operator fun V2.plus(o: V2) = V2(x + o.x, y + o.y)
operator fun V2.minus(o: V2) = V2(x - o.x, y - o.y)

operator fun V2.div(a: Number) = V2(x / a.toDouble(), y / a.toDouble())
operator fun V2.times(a: Number) = V2(x * a.toDouble(), y * a.toDouble())
operator fun Number.times(v: V2) = v * this



infix fun V2.eq(o: V2) = (x eq o.x) && (y eq o.y)
val V2.isZero : Boolean get() = this eq V2.O


infix fun V2.dot(o: V2) : Double = x * o.x + y * o.y
infix fun V2.cross(o: V2) : Double = x * o.y - y * o.x

val V2.norm get() = Math.hypot(x, y)
val V2.norm2 get() = this dot this

fun V2.dist2(o: V2) = (this - V2.O).norm2
fun V2.dist(o: V2) = (this - V2.O).norm

fun V2.isColinear(o: V2) = this cross o eq 0.0



