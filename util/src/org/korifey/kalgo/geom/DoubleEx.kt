package org.korifey.kalgo.geom

var EPS = 1e-06
infix fun Double.eq(o: Double) = Math.abs(this - o) < EPS
infix fun Double.gt(o: Double) = this > o.toDouble() && !(this eq o)
infix fun Double.lt(o: Double) = this < o.toDouble() && !(this eq o)
infix fun Double.gte(o: Double) = this > o.toDouble() || (this eq o)
infix fun Double.lte(o: Double) = this < o.toDouble() || (this eq o)


val Double.isZero : Boolean get() = eq(0.0)
fun Double.sqr() = this * this
