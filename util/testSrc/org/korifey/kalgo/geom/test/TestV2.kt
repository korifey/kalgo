package org.korifey.kalgo.geom.test

import org.junit.Test

class TestV2 {
    @Test
    fun testInfinities() {
        println(Double.NEGATIVE_INFINITY - Double.NEGATIVE_INFINITY)
        println(Double.NEGATIVE_INFINITY < Double.NEGATIVE_INFINITY)
        println(Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY)
    }
}