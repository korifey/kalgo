
import java.util.*
import kotlin.math.max
import kotlin.math.min


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    outer@for (case in 1..t) {
        val n = scanner.nextInt()
        val l = scanner.nextLine().split(" ")

        var mn = 10000000
        var mx = 0

        for (i in 1..n) {
            val l = scanner.nextLine().split(" ")
            val first = l[0]
            val second = l[1]
            val ryhm = l[2] == "1"
            val len = min(first.length, second.length)

            var x = 0

            for (i in 0 until len) {
                if (first[first.length - i - 1] == second[second.length - i - 1]) {
                    x++
                } else {
                    break
                }
            }

            if (ryhm) {
                mn = min(mn, x)
            } else {
                mx = max(mx, x + 1)
            }
        }
        println(max(0, mn - mx + 1))
        for (i in mx..mn) {
            print("$i ")
        }
        println()
    }
}