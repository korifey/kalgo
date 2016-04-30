package org.korifey.kalgo.gcj.y2016.round1a



import org.korifey.kalgo.gcj.base.GcjBase
import org.korifey.kalgo.util.digitsFromLoToHi
import java.io.PrintStream
import java.util.*

internal class A : GcjBase() {
    override fun case(scanner: Scanner, out: PrintStream) {
        var s = scanner.next()
        var cur : Char = 0.toChar()
        var res = ""
        for (c in s) {
            if (c>=cur) {
                res = c + res
                cur = c
            } else {
                res = res + c
            }
        }
        out.print(res)
    }

}