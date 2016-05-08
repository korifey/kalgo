package org.korifey.kalgo.gcj.y2016.round1c

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class C : GcjBase() {


    val generated = arrayOf(0 to 0,
        9 to 22102794,
        18 to 62580637,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727,
        27 to 134217727
            )
    fun calc(j: Int, p: Int, s: Int, k: Int) : Pair<Int, Int> {
        if (j == 3 && p == 3 && s == 3) {
            //generated
            return generated[k]
        }

        var max = 0
        var best = 0
        val jp = IntArray(j*p)
        val js = IntArray(j*s)
        val ps = IntArray(p*s)
        cycle@for (binary in 0..(1 shl (j*p*s)) -1) {
            jp.fill(0)
            js.fill(0)
            ps.fill(0)

            var x = 0
            var cur = 0

            for (dj in 0..j-1) {
                for (dp in 0..p-1) {
                    for (ds in 0..s-1) {
                        if (binary or (1 shl x) == binary) {
                            if (++jp[dj*p + dp] > k) continue@cycle
                            if (++js[dj*s + ds] > k) continue@cycle
                            if (++ps[dp*s + ds] > k) continue@cycle
                            cur++
                        }
                        x++
                    }
                }
            }
            if (cur > max) {
                max = cur
                best = binary
            }

        }

        return max to best
    }


    override fun case(scanner: Scanner, out: PrintStream) {
        var j = scanner.nextInt()
        var p = scanner.nextInt()
        var s = scanner.nextInt()
        var k = scanner.nextInt()

        val (max, best) = calc(j, p, s, k)
        out.print(max)

        var x = 0
        for (dj in 0..j-1) {
            for (dp in 0..p-1) {
                for (ds in 0..s-1) {
                    if (best or (1 shl x) == best) {
                        out.println()
                        out.print("${dj+1} ${dp+1} ${ds+1}")
                    }
                    x++
                }
            }
        }
    }


}

fun main(args: Array<String>) {
//    var arr = Array<Array<Array<Pair<Int, Int>>>>(3)
    for (k in 1..10) {
        val (max, best) = C().calc(3, 3, 3, k)
        println("k=$k: $max to $best")
    }
}