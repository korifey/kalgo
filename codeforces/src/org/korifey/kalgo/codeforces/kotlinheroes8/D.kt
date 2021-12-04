
import java.util.*
import kotlin.math.max
import kotlin.math.min


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val m = scanner.nextInt()

    val a = hashMapOf<Int, Int>()
    val b = hashMapOf<Int, Int>()
    val c = hashMapOf<Pair<Int, Int>, Int>()

    var f0 = 0
    var l0 = 0
    for (i in 1..m) {

        val f = scanner.nextInt()
        val l = scanner.nextInt()
        if (i == 1) {
            f0 = f
            l0 = l
        }

        a[f] = a.getOrDefault(f, 0) + 1
        b[l] = b.getOrDefault(l, 0) + 1

        c[f to l] = c.getOrDefault(f to l, 0) + 1
    }

    var res = 1
    for (i in 1..n) {
        for (j in 1..n) {
            if (i == j) continue


            if ((f0 == i && l0 != j) || (f0 != i && l0 == j)) {
                res = max(res, c.getOrDefault(i to j, 0) + 1)
            } else if (f0 != i && l0 != j) {
                val position = a.getOrDefault(i, 0)  +
                        b.getOrDefault(j, 0) -
                        c.getOrDefault(i to j, 0)
                res = max(res, position + 1)
            }
        }
    }

    println(res)
}