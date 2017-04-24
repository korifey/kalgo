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

    class CountSet<T> : Iterable<Pair<T, Int>> {
        override fun iterator(): Iterator<Pair<T, Int>> = map.asIterable().map { it.key to it.value }.iterator()

        val map = LinkedHashMap<T, Int>()
        fun remove(t: T) = add(t, -1)
        fun add(t:T, value: Int = 1) {
            map.merge(t, value) {old, new -> (old + new)}
            if (map[t] == 0) map.remove(t)
        }
        operator fun get(t: T) = map[t] ?: 0
    }

    val n = scan.int()
    val x = scan.int()
    val set = CountSet<Int>()
    for (i in 1..n) set.add(scan.int())

    var res2 = 0L
    for ((v, cnt) in set) {
        val xor = v.xor(x)
        if (xor == v) res2 += cnt.toLong() * (cnt - 1)
        else res2 += cnt.toLong() * set[xor]
    }

    exit(res2 / 2)

}

    