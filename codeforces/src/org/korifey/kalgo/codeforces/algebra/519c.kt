import java.util.*
import java.io.*
import java.lang.Math.*


private fun exit(msg: String) {
    println(msg)
    System.exit(0)
}
private fun exit(msg: Int) = exit("$msg")

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
    val m = scan.int()
    val (min, max) = min(n,m) to max(n,m)
    val sum = if (max > min*2) min*3 else min+max
    exit(sum/3)
}

    