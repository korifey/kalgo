
import java.util.*


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    scanner.nextLine()

    outer@for (case in 1..t) {
        val s = scanner.nextLine()
        var ltPresent = false
        var gtPresent = false
        for (c in s) when(c) {
            '<' ->  if (gtPresent) {
                println("?")
                continue@outer
            } else {
                ltPresent = true
            }
            '>' ->  if (ltPresent) {
                println("?")
                continue@outer
            } else {
                gtPresent = true
            }
            else -> {}
        }
        if (ltPresent)
            println("<")
        else if (gtPresent)
            println(">")
        else
            println("=")
    }
}