import java.lang.Math.*
import java.util.*


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val x = scanner.nextInt()

    val res = (1..min(x,n)).count { x % it == 0 && x / it <= n}
    println(res)
}
