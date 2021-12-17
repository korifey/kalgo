import java.util.*

fun main(args: Array<String>) {
    val a = input.readLines().map { s -> s.map { c -> c -'0'}.toIntArray() }.toTypedArray()

    val n = a.size
    val m = a[0].size

    val res = Array(n) {IntArray(m) {-1} }

    val q = PriorityQueue<Pair<Int, Int>> (compareBy { (i,j) -> res[i][j] })
    q.add(0 to 0)
    res[0][0] = 0


    val candidates = mutableListOf<Pair<Int, Int>>()
    fun add(acc:Int, i: Int, j: Int) {
        if (i<0 || i>=n || j<0 || j>= m)
            return
        if (res[i][j] != -1)
            return

        res[i][j] = acc + a[i][j]
        candidates.add(i to j)
    }


    while (q.isNotEmpty()) {
        val (i,j) = q.poll()
        candidates.clear()
        add(res[i][j], i+1, j)
        add(res[i][j], i-1, j)
        add(res[i][j], i, j-1)
        add(res[i][j], i, j+1)
        q.addAll(candidates)
    }

    println(res[n-1][m-1])
}

