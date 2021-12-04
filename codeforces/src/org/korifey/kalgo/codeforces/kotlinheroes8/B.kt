
import java.util.*
import kotlin.math.min


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    outer@for (case in 1..t) {
        val n = scanner.nextInt()
        val a = scanner.nextInt()
        val va = scanner.nextInt()
        val c = scanner.nextInt()
        val vc = scanner.nextInt()
        val b = scanner.nextInt()

        val res = min(va + b - a, vc)
        println(res)
    }
}