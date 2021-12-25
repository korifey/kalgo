package p20

import input
import org.korifey.kalgo.geom.*
import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Math.abs
import java.util.ArrayDeque



fun main(args: Array<String>) {

    var flag = true
    val (enhancement, _picture) = input.readLines().partition {
        if (it.isBlank()) flag = false
        flag
    }
    val e = enhancement.joinToString("").map { if (it == '.') 0 else 1 }.toIntArray()
    require(e.size == 512)

    val offset = 50
    val picture = _picture.drop(1)
    val n = picture.size
    val m = picture[0].length

    var outer = 0 //out of field

    var a = Array(2 * offset + n) { IntArray(2 * offset + m) }
    for (i in offset until n + offset)
        for (j in offset until m + offset) {
            a[i][j] = if (picture[i - offset][j - offset] == '.') 0 else 1
        }

    fun g(i: Int, j: Int) = if (i in a.indices && j in a[0].indices) a[i][j] else outer

    var o = offset
    while (--o >= 0) {
        val nextOuter = e[if (outer == 0) 0 else 511]
        val b = Array(2 * offset + n) { IntArray(2 * offset + m) { nextOuter } }
        for (i in o until n + 2*offset - o)
            for (j in o until m + 2*offset - o) {
                val x =
                    (g(i-1, j-1) shl 8) + (g(i-1, j) shl 7) + (g(i-1, j+1) shl 6) +
                            (g(i, j-1) shl 5) + (g(i, j) shl 4) + (g(i, j+1) shl 3) +
                            (g(i+1, j-1) shl 2) + (g(i+1, j) shl 1) + (g(i+1, j+1) shl 0)
                b[i][j] = e[x]
            }
        a = b
        outer = nextOuter

        if (offset-o == 2)
            println("p20="+a.sumOf { it.count { it == 1 } })
    }
    println("p20x="+a.sumOf { it.count { it == 1 } })
}
