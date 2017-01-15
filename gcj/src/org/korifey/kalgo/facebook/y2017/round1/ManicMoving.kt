package org.korifey.kalgo.facebook.y2017.round1

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

class ManicMoving : GcjBase() {

    override fun case(scanner: Scanner, out: PrintStream) {
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val k = scanner.nextInt()

        val illegal = Integer.MAX_VALUE.toLong()
        val path = Array (n+1) { i -> LongArray(n+1) { j ->
            if (i == j) 0
            else illegal
        }}

        for (tmp in 1..m) {
            val a = scanner.nextInt()
            val b = scanner.nextInt()
            val g = scanner.nextLong()
            if (path[a][b] > g) {
                path[a][b] = g
                path[b][a] = g
            }
        }

        for (i in 1..n)
            for (j in 1..n)
                for (x in 1..n)
                    if (path[i][x] + path[x][j] < path[i][j]) {
                        path[i][j] = path[i][x] + path[x][j]
                        path[j][i] = path[i][x] + path[x][j]
                    }

        val start = IntArray(k+1)
        val fin = IntArray(k+1)
        for (s in 1..k) {
            start[s] = scanner.nextInt()
            fin[s] = scanner.nextInt()
        }

        for (s in 1..k) {
            if (path[1][start[s]] == illegal || path[start[s]][fin[s]] == illegal) {
                out.print(-1)
                return
            }
        }

        val loadedOne = LongArray(k+1)
        val loadedTwo = LongArray(k+1)
        val deliveredPart = LongArray(k+1)
        val deliveredAll = LongArray(k+1)
        loadedOne[1] = path[1][start[1]]

        for (s in 1..k) {
            if (s > 1) {
                loadedOne[s] = deliveredAll[s-1] + path[fin[s-1]][start[s]]

                loadedTwo[s] = loadedOne[s-1] + path[start[s-1]][start[s]]
                if (s > 2) loadedTwo[s] = Math.min(loadedTwo[s], deliveredPart[s-1] + path[fin[s-2]][start[s]])

                deliveredPart[s] = loadedTwo[s] + path[start[s]][fin[s-1]]
            }

            deliveredAll[s] = loadedOne[s] + path [start[s]][fin[s]]
            if (s > 1) deliveredAll[s] = Math.min(deliveredAll[s], deliveredPart[s] + path[fin[s-1]][fin[s]])

        }
        out.print(deliveredAll[k])
    }

}