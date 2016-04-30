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

        val res = LinkedList<Int>()
        res.add(0)


        fun rec() :Boolean {
            val prev = res.last
            if (sum == 0) {
                res.removeFirst()
                out.print(res.joinToString (""))
                return true
            }
            for (i in prev..9) {
                if (sub(i)) {
                    res.add(i)
                    if (rec()) return true
                    res.removeLast()
                }
                add(i)
            }
            return false
        }

        require(rec()) {"must be true"}
    }
}