package org.korifey.kalgo.facebook.y2017.round1

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

class PieProgress : GcjBase() {
    override fun case(scanner: Scanner, out: PrintStream) {
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val a = Array(n) {LongArray(m)}
        val avail = IntArray(n) {0}

        var res : Long = 0L

        for (i in 0..n-1) {
            for (j in 0..m-1) a[i][j] = scanner.nextLong()
            Arrays.sort(a[i])
            for (j in 0..m-1) a[i][j] += (2*j + 1).toLong()

            var (minI, minJ) = i to 0
            for (i1 in i-1 downTo 0) {
                if (avail[i1] >= m) continue

                if (a[i1][avail[i1]] < a[minI][minJ]) {
                    minI = i1
                    minJ = avail[i1]
                }
            }

            res += a[minI][minJ]
            avail[minI]++
        }

        out.print(res)
    }

}