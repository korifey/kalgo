import java.util.*
import java.io.*
import java.lang.Math.*


private fun exit(msg: String) {
    println(msg)
    System.exit(0)
}
fun main(args: Array<String>) {
    val scan = object {
        private val reader = BufferedReader(InputStreamReader(System.`in`))
        private var tokenizer: StringTokenizer? = null
    
        internal operator fun next(): String {
            var t = tokenizer
            while (t == null || !t.hasMoreTokens()) {
                t = StringTokenizer(line())
            }
            return t.nextToken().apply { tokenizer = t }
        }
    
        internal fun int(): Int = next().toInt()
        internal fun long(): Long = next().toLong()
        internal fun double() = next().toDouble()
    
        internal fun line() = reader.readLine()
    
    }
    val n = scan.int()
    if (n == 0) exit("1")
    when (n % 4) {
        0 -> exit("6")
        1 -> exit("8")
        2 -> exit("4")
        3 -> exit("2")
    }
    
}

    