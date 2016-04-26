package org.korifey.kalgo.util
import kotlin.system.measureTimeMillis

private val zeroIntSequence = sequenceOf(0)
fun Int.digitsFromLoToHi(radix: Int = 10) : Sequence<Int> {
    if (this == 0) return zeroIntSequence

    return Sequence {
        object:Iterator<Int> {
            var x = this@digitsFromLoToHi
            override fun hasNext() = x > 0
            override fun next() = (x % radix).apply { x /= radix }
        }
    }
}

fun Int.digitsFromHiToLo(radix: Int = 10) = digitsFromLoToHi(radix).asIterable().reversed()


private val zeroLongSequence = sequenceOf(0L)
fun Long.digitsFromLoToHi(radix: Int = 10) : Sequence<Long> {
    if (this == 0L) return zeroLongSequence

    return Sequence {
        object:Iterator<Long> {
            var x = this@digitsFromLoToHi
            override fun hasNext() = x > 0
            override fun next() = (x % radix).apply { x /= radix }
        }
    }
}

fun Long.digitsFromHiToLo(radix: Int = 10) = digitsFromLoToHi(radix).asIterable().reversed()


fun main(args: Array<String>) {
    measureTimeMillis {
        var res = 0;
        (0..1000000).forEach {
            res += it.digitsFromHiToLo().count()
        }
    }.apply { println("$this ms") }

    measureTimeMillis {
        var res = 0;
        (0..1000000).forEach {
            res += it.digitsFromLoToHi().count()
        }
    }.apply { println("$this ms") }


    val xx = 10203040506070809L.digitsFromHiToLo()
    println(xx.joinToString(""))
}