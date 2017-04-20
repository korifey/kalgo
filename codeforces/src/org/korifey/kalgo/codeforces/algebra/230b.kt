
import java.util.*
import java.io.*


class BoolSet_230b(trueCount: Int, private val array: BooleanArray) : Iterable<Int> {
    companion object {
        fun fromArray(array: BooleanArray): BoolSet_230b = BoolSet_230b(array.count { it }, array)
        fun empty(n: Int) = BoolSet_230b(0, BooleanArray(n))
        fun full(n: Int) = BoolSet_230b(n, BooleanArray(n, { true }))
    }

    override fun iterator() = object : Iterator<Int> {
        var cnt = 0
        override fun hasNext(): Boolean {
            while (cnt < array.size && !array[cnt]) cnt++
            return (cnt < array.size)
        }

        override fun next(): Int {
            while (!array[cnt]) cnt++
            return cnt++
        }
    }

    var count: Int = trueCount
        get
        private set

    operator fun get(idx: Int) = idx in 0..count - 1 && array[idx]
    operator fun set(idx: Int, value: Boolean) {
        if (value != array[idx]) {
            count += if (value) 1 else -1
            array[idx] = value
        }
    }

    fun getInternalBooleanArray() = array
}


fun main(args: Array<String>) {
    fun eratosthenes(n: Int): BoolSet_230b {
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
        return BoolSet_230b.fromArray(res)
    }

    data class Boxed<T>(var value: T)

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

        fun int(): Int = next().toInt()
        internal fun long(): Long = next().toLong()
        internal fun double() = next().toDouble()

        internal fun line() = reader.readLine()

    }

    val n = scan.int()
    val a = LongArray(n) { scan.long() }
    val m = a.associate { it to Boxed(false) }
    eratosthenes(1000000).forEach {
        val sq = it.toLong() * it.toLong()
        m[sq]?.value = true
    }
    a.forEach {
        if (m[it]!!.value) println("YES")
        else println("NO")
    }
}