package org.korifey.kalgo.gcj.y2016.round2



import org.korifey.kalgo.gcj.base.GcjBase
import org.korifey.kalgo.util.digitsFromLoToHi
import java.io.PrintStream
import java.util.*

internal class A : GcjBase() {
    override fun case(scanner: Scanner, out: PrintStream) {
        var n = scanner.nextInt()
        val r = scanner.nextInt()
        val p = scanner.nextInt()
        val s = scanner.nextInt()

//        val limit = 1 shl n
//        val a = IntArray(limit)
//        val b = IntArray(limit)
//
//        fun verify() : Boolean {
//            for (i in 0..a.lastIndex) b[i] = a[i]
//            var len = limit
//            while (len > 1)
//            {
//                var i = 0
//                while (i < len) {
//                    if (b[i] == b[i+1]) return false;
//                    b[i/2] = when (b[i]) {
//                        0 -> if (b[i+1] == 1) 1 else 0
//                        1 -> if (b[i+1] == 2) 2 else 1
//                        2 -> if (b[i+1] == 0) 0 else 2
//                        else -> throw IllegalArgumentException()
//                    }
//                    i+= 2
//                }
//                len /= 2
//            }
//            return true;
//        }
//
//        fun rec(pos: Int, rr:Int, pp:Int, ss:Int) : Boolean {
//            if (pos == limit) {
//                if (verify()) {
//                    a.map { when (it) {
//                        0 -> 'P'
//                        1 -> 'S'
//                        2 -> 'R'
//                        else -> throw IllegalArgumentException()
//                    } }.forEach { out.print(it) }
//                    return true
//                };
//                return false;
//            } else {
//                if (pp > 0) {
//                    a[pos] = 0
//                    if (rec(pos+1, rr, pp-1, ss)) return true;
//                }
//
//                if (rr > 0) {
//                    a[pos] = 2;
//                    if (rec(pos+1, rr-1, pp, ss)) return true;
//                }
//
//                if (ss > 0) {
//                    a[pos] = 1;
//                    if (rec(pos+1, rr, pp, ss-1)) return true;
//                }
//
//                return false;
//            }
//        }
//
//        if (!rec(0, r, p, s)) out.print("IMPOSSIBLE")
//

        var pp = "P"
        var ss = "S"
        var rr = "R"

        for (i in 1..n) {
            val ppNext = if (pp < rr) pp + rr else rr + pp
            val ssNext = if (ss < pp) ss + pp else pp + ss
            val rrNext = if (rr < ss) rr + ss else ss + rr

            pp = ppNext
            ss = ssNext
            rr = rrNext
        }

        val a = Array<String>(3, {""})
        a[0] = pp
        a[1] = ss
        a[2] = rr
        a.sort()
        for (res in a) {
            if (
            res.count { it == 'P' } == p &&
            res.count { it == 'S' } == s &&
            res.count { it == 'R' } == r
            )
            {
                out.print(res)
                return
            }
        }
        out.print("IMPOSSIBLE")
    }

}