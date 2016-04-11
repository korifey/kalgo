package org.korifey.kalgo.gcj.y2016.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

class D : GcjBase() {
    override fun case(scanner: Scanner, out: PrintStream) {
        val k = scanner.nextInt()
        val c = scanner.nextInt()
        val s = scanner.nextInt()

        out.print((1..s).joinToString(" "))
    }

}
