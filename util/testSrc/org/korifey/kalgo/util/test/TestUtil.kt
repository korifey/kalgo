package org.korifey.kalgo.util.test

fun <T> assertIterableEqual(x: Iterable<T>, y: Iterable<T>) {
    val (expected, other) = x.iterator() to y.iterator()
    var cnt = 0
    while (expected.hasNext() && other.hasNext()) {
        val a1 = expected.next()
        val a2 = other.next()

        if (a1 != a2) throw AssertionError("Diff at position $cnt: '$a1' != '$a2'")
        cnt++
    }
    if (expected.hasNext()) throw AssertionError("Expected is longer: ${x.joinToString()} vs ${y.joinToString()}")
    if (other.hasNext()) throw AssertionError("Other is longer: ${x.joinToString()} vs ${y.joinToString()}")

}