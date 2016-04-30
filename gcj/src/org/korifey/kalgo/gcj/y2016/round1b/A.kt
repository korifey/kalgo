package org.korifey.kalgo.gcj.y2016.round1b

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class A : GcjBase() {


    val a = arrayOf("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE")
    val p = IntArray(26)
    var sum = 0
    fun add(x: Int) {
        val s = a[x]
        s.forEach { c ->
            p [c - 'A'] ++
            sum++
        }
    }
    fun sub(x: Int) : Boolean {
        var res = true
        val s = a[x]
        s.forEach { c ->
            if (--p [c - 'A'] < 0) res = false
            sum --
        }
        return res
    }







    override fun case(scanner: Scanner, out: PrintStream) {
        var s = scanner.next()
        p.fill(0)
        sum = 0
        s.forEach { c ->
            p[c - 'A']++
            sum ++
        }

        val res = IntArray(10)
        val xx = arrayOf('Z' to 0, 'W' to 2, 'U' to 4, 'X' to 6, 'G' to 8, 'F' to 5, 'O' to 1, 'S' to 7, 'T' to 3, 'N' to 9)
        for (pair in xx) {
            val (letter, i) = pair
            while (p[letter-'A'] > 0) {
                res[i]++
                sub(i)
            }
        }
        require(sum == 0)
        for (i in 0..9)
        {
            for (j in 1..res[i]) out.print(i)
        }
    }
}