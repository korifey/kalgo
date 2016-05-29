package org.korifey.kalgo.gcj.y2016.round2



import org.korifey.kalgo.gcj.base.GcjBase
import org.korifey.kalgo.util.digitsFromLoToHi
import java.io.PrintStream
import java.util.*

internal class B : GcjBase() {

    data class X(val n : Int, val k:Int, val v:Int)

    override fun case(scanner: Scanner, out: PrintStream) {
        var N = scanner.nextInt()
        var K = scanner.nextInt()
        val A = DoubleArray(N)
        for (i in 0..N-1) A[i] = scanner.nextDouble()

        val memo = HashMap<X, Double>(N*N*N*2)
        val maxW = DoubleArray(N)
        val maxL = DoubleArray(N)

        maxW[0] = A[0]
        maxL[0] = 1-A[0]
        for (i in 1..N-1) {
            maxW[i] = Math.max(maxW[i-1], A[i])
            maxL[i] = Math.max(maxL[i-1], 1-A[i])
        }

        fun f(n: Int, k: Int, v: Int) : Double {
            if (k > n || Math.abs(v) > k) return 0.0

            val key = X(n, k, v)
            memo[key]?.let { return it }

            val res =
                if (k == 1) {
                    when (v) {
                    0 -> 0.0
                    1 -> maxW[n-1]
                    -1 -> maxL[n-1]
                    else -> throw IllegalArgumentException()
                    }
                } else {


                    Math.max(f(n-1, k, v),

                            f(n-1, k-1, v-1)*A[n-1]
                                    + f (n-1, k-1, v+1)*(1-A[n-1]))
                }
            memo[key] = res
            return res
        }

        val res = f(N,K,0)
        out.print(res)
    }

}