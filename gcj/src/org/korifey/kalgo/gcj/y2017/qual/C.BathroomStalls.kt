package org.korifey.kalgo.gcj.y2017.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class C : GcjBase() {

    data class Interval(val l: Int, val r: Int) : Comparable<Interval> {
        override fun compareTo(other: Interval): Int {
            val x = -((r-l).compareTo(other.r - other.l))
            if (x != 0) return x
            return l.compareTo(other.l)
        }

    }

    override fun case(scanner: Scanner, out: PrintStream) {
        val n = scanner.nextInt()
        val k = scanner.nextInt()

        val q = PriorityQueue<Interval>()
        q.offer(Interval(1, n))
        var ls = 0
        var rs = 0
        for (i in 1..k) {
            val (l, r) = q.poll()
            val m = (l+r)/2
            ls = m - l
            rs = r - m
            if (m - 1 >= l) q.offer(Interval(l, m-1))
            if (m + 1 <= r) q.offer(Interval(m+1, r))
        }
        out.print("" + Math.max(ls,rs) + " " + Math.min(ls, rs))
    }
}
