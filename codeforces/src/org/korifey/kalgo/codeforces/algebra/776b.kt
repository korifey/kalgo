import java.util.*
import java.io.*
import java.lang.Math.*


private fun exit(msg: String) {
    println(msg)
    System.exit(0)
}
private fun exit(msg: Int) = exit(""+msg)

private fun eratosthenes(n: Int): BooleanArray {
    val res = BooleanArray(n + 1, { true })

    res[0] = false
    res[1] = false

    var s = 2
    while (s * s <= n) {
        if (!res[s]) {
            s++
            continue
        }
        var i = s * s
        while (i <= n) {
            res[i] = false
            i += s
        }
        s++
    }
    return res
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
    if (n <= 2) {
        println("1")
        print("1")
        if (n == 2) println(" 1")
    } else {
        println("2")
        val a = eratosthenes(n+1)
        for (i in 2..a.lastIndex) {
            if (a[i]) print("1 ")
            else print("2 ")
        }
    }
}

    