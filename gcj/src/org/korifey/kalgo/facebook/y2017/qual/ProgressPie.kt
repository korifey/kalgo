package org.korifey.kalgo.facebook.y2017.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.lang.Math.atan2
import java.util.*


data class V(val x: Int, val y:Int) {
    val dist2 : Int get() = x * x + y * y
}

class ProgressPie : GcjBase() {
    
    override fun case(scanner: Scanner, out: PrintStream) {
        val p = scanner.nextInt()
        val x = scanner.nextInt()
        val y = scanner.nextInt()

        if (p == 0) out.print("white").let { return }

        val v = V(x - 50, y - 50)
        if (v.dist2 > 50*50) out.print("white").let { return }

        if (v.x == 0) out.print("black").let { return }
        var angle = atan2(v.x.toDouble(), v.y.toDouble())
        if (angle < 0) angle += 2*Math.PI

        val diff = (p*2*Math.PI / 100) - angle
        if (diff + 1E-06 > 0) out.print("black").let { return }
        else out.print("white").let { return }
    }
}