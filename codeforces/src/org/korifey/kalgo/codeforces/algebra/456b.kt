import java.util.*
import java.io.*
import java.lang.Math.*


private fun exit(msg: String) {
    println(msg)
    System.exit(0)
}
private fun exit(msg: Int) = exit(""+msg)


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
    val l = scan.line()
    val l2 = if (l.length == 1) l else l.substring(l.lastIndex-1)
    val n = l2.toInt() % 4
    val res = (1..4).sumBy { (1..n).fold(1) {acc, _t -> acc * it} } % 5
    exit(res)
}

    