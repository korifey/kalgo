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
    val n = scan.int()
    val a = IntArray(n) {
        var p = scan.int()
        while (p>1 && p % 2 == 0) p/=2
        while (p>1 && p % 3 == 0) p/=3
        p
    }
    if (a.all { it == a[0] })
        exit("Yes")
    else
        exit("No")
}

    