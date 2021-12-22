package org.korifey.kalgo.geom

import kotlin.math.abs

data class V3I(val x: Int, val y: Int, val z: Int) {
    companion object {
        val O = V3I(0, 0, 0)
        val OX = V3I(1, 0, 0)
        val OY = V3I(0, 1, 0)
        val OZ = V3I(0, 0, 1)
    }
    fun toAnotherBasis(originalBasicInAnotherBasis: BasisV3I) : V3I =
        x * originalBasicInAnotherBasis.ox +
        y * originalBasicInAnotherBasis.oy +
        z * originalBasicInAnotherBasis.oz
}

operator fun V3I.plus(o: V3I) = V3I(x + o.x, y + o.y, z + o.z)
operator fun V3I.minus(o: V3I) = V3I(x - o.x, y - o.y, z - o.z)
operator fun V3I.unaryMinus() = V3I.O - this

operator fun V3I.times(a: Int) = V3I(x * a, y * a, z * a)
operator fun Int.times(v: V3I) = v * this



val V3I.isZero : Boolean get() = this == V3I.O


infix fun V3I.dot(o: V3I) : Int = x * o.x + y * o.y + z*o.z
infix fun V3I.cross(o: V3I) : V3I = V3I(y*o.z - z*o.y, z*o.x - x * o.z, x * o.y - y * o.x)

data class BasisV3I(val ox: V3I, val oy: V3I, val oz: V3I) {
    companion object {
        val default = BasisV3I(V3I.OX, V3I.OY, V3I.OZ)
    }
}

fun V3I.normmax() = maxOf(abs(x), abs(y), abs(z))
