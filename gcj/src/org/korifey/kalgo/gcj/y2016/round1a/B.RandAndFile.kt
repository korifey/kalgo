package org.korifey.kalgo.gcj.y2016.round1a



import org.korifey.kalgo.gcj.base.GcjBase
import org.korifey.kalgo.gcj.base.nextIntArray
import org.korifey.kalgo.util.digitsFromLoToHi
import java.io.PrintStream
import java.util.*

internal class B : GcjBase() {


    override fun case(scanner: Scanner, out: PrintStream) {
        var n = scanner.nextInt()
        var a = ArrayList<IntArray>(2*n-1)
        for (i in 1..2*n-1) {
            a.add(scanner.nextIntArray(n))
        }


    }

}