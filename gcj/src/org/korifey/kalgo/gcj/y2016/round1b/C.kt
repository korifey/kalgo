package org.korifey.kalgo.gcj.y2016.round1b

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class C : GcjBase() {


    override fun case(scanner: Scanner, out: PrintStream) {
        val n = scanner.nextInt()

        val m1 = HashMap<String, Int>()
        val m2 = HashMap<String, Int>()
        var gen1 = 0
        var gen2 = 0

        val a = Array<Pair<Int, Int>>(n, {0 to 0})

        (0..n-1).forEach { i ->
            var s1 = scanner.next()
            var s2 = scanner.next()

            val x1 = m1.get(s1) ?: gen1++.apply { m1.put(s1, this)}
            val x2 = m2.get(s2) ?: gen1++.apply { m2.put(s2, this)}
            a[i] = x1 to x2
        }

        var max = 0
        cycle@for(binary in 1..((1 shl n) -1)) {
            var x = 1

            val set = BooleanArray(50)
            var fake = 0
            (0..n-1).forEach { i->
                if ((binary or x) == binary) {
                    set[a[i].first] = true
                    set[a[i].second] = true
                } else {
                    fake++
                }
                x = x shl 1
            }

            for (i in 0..n-1){
                if (!set[a[i].first] || !set[a[i].second]) continue@cycle
            }
            max = Math.max(max,fake)
        }

        out.print(max)
    }
}