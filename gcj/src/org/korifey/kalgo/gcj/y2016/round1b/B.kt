package org.korifey.kalgo.gcj.y2016.round1b

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class B : GcjBase() {
    override fun case(scanner: Scanner, out: PrintStream) {
        val c = scanner.next()
        val j = scanner.next()

        val cQmark = (c).filter { it == '?' } .count()
        val jQmark = (j).filter { it == '?' } .count()
        val qmarks = cQmark + jQmark

        val cInt = c.replace('?','0').toInt()
        val jInt = j.replace('?','0').toInt()




        val x = LinkedList<Int>()
        var best = 100000
        var minC = 10
        var minJ = 10

        fun rec(d: Int) {
            if (d == qmarks) {

                var cMod = cInt;
                var jMod = jInt
                var pow = 1

                val xClone = x.clone() as LinkedList<Int>

                    for (k in c.lastIndex downTo 0) {
                        if (c[k] == '?') cMod += xClone.removeFirst()*pow
                        pow*=10;
                    }


                pow = 1

                    for (k in j.lastIndex downTo 0) {
                        if (j[k] == '?') jMod += xClone.removeFirst()*pow
                        pow*=10;
                    }


                val abs = Math.abs(cMod - jMod)
                if (abs < best
                    || abs == best && cMod < minC
                    || abs == best && cMod == minC && jMod < minJ
                ) {
                    best = abs
                    minC = cMod
                    minJ = jMod
                }
            } else {
                for (i in 0..9) {
                    x.add(i)
                    rec(d + 1)
                    x.removeLast()
                }
            }
        }

        rec(0)

        out.print(minC.toString().padStart(c.length, '0'))
        out.print(" ")
        out.print(minJ.toString().padStart(j.length, '0'))
    }
}