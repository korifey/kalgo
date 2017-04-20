import java.util.*
import java.io.*
import java.lang.Math.*


private fun exit(msg: String) {
    println(msg)
    System.exit(0)
}
private fun exit(msg: Long) = exit(""+msg)


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

    val xi = {x:Int ->
        LongArray(5) {i -> x / 5L + if (x % 5 >= i % 5) 1 else 0}.apply { this[0] -- }
    }
    val ni = xi(n)
    val mi = xi(m)

    val res = ni.mapIndexed { index, x -> x * mi[(5-index) % 5] }.sum()
    exit(res)
}

    