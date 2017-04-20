import java.util.*
import java.io.*
import java.lang.Math.*


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
    var n = scan.int()
    var (p2,p3) = 0 to 0
    while (n > 0) {
        if (n > 3 || n == 2) {
            p2 ++
            n -= 2
        } else if (n == 3) {
            p3 ++
            n -= 3
        }
    }
    println(p2+p3)
    for (i in 1..p2) print("2 ")
    for (i in 1..p3) print("3 ")


    
}

    