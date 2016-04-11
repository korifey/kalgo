package org.korifey.kalgo.gcj.y2016.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

class C : GcjBase() {

    private fun powMod(radix: Int, n: Int, mod: Int) : Int {
        var res = 1
        for (i in 1..n) {
            res = (res*radix) % mod
        }
        return res
    }

    private fun nontrivialDivisor(x : Long, n: Int, radix : Int) : Int? {
        var i = 2;
        while (i*i <= 1000000000) {

            if ((x + powMod(radix, n, i))% i == 0L) return i
            else i++
        }
        return null
    }

    override fun case(scanner: Scanner, out: PrintStream) {
        val N = scanner.nextInt()
        val J = scanner.nextInt()

        var res = 0
        val lim = (1 shl (N-2)) - 1
        outer@for (i in 0..lim) {
            if (res == J) break;

            var s = Integer.toBinaryString(i)+"1"
            var divlst = arrayListOf<Int>()
            for (radix in 2..10) {
                val x = java.lang.Long.parseLong(s, radix)
                val divisor = nontrivialDivisor(x, N-1, radix);
                if (divisor == null) continue@outer
                else divlst.add(divisor)
            }

            res ++
            out.println()
            out.print("1"+s.padStart(N-1, '0'))
            for (d in divlst) out.print(" $d")

        }
    }

}

