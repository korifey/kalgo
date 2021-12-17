import java.util.*

fun main(args: Array<String>) {
    val a = input.readLines().map { s -> s.map { c -> c -'0'}.toIntArray() }.toTypedArray()

    val n0 = a.size
    val m0 = a[0].size

    fun aa(i:Int, j: Int) : Int{
        val res = a[i % n0][j % m0] + i / n0 + j /m0
        return res - 9.cnd(res > 9)
    }



    val n = n0 * 5
    val m = m0 * 5


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

        res[i][j] = acc + aa(i, j)
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

