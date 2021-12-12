import java.util.*

fun main(args: Array<String>) {
    val a = input.readLines().map { s -> s.map { c -> c -'0'}.toIntArray() }.toTypedArray()
    val n = a.size
    val m = a[0].size

    fun height(i: Int, j: Int): Int {
        if (i<0 || i>=n || j<0 || j>= m)
            return 10
        else return a[i][j]
    }

    fun legal(i: Int, j: Int): Boolean {
        return !(i<0 || i>=n || j<0 || j>= m)
    }



    val basins = mutableListOf<Int>()

    for (i in 0 until n) {
        for (j in 0 until m) {
            val h = a[i][j]
            if (height(i-1,j) > h && height(i+1,j)>h && height(i,j-1) > h && height(i,j+1)>h)
            {
                var basin = 0
                val visited = Array(n) { BooleanArray(m) }
                val q = ArrayDeque<Pair<Int, Int>>()



                fun add(i0:Int,j0:Int) {
                    if (i0<0 || i0>=n || j0<0 || j0>= m) return
                    if (visited[i0][j0]) return
                    if (a[i0][j0] == 9) return
                    visited[i0][j0] = true
                    q.add(i0 to j0)
                    basin ++
                }
                add(i,j)

                while (q.isNotEmpty()) {
                    val (i0,j0) = q.poll()
                    add(i0-1, j0)
                    add(i0+1, j0)
                    add(i0, j0-1)
                    add(i0, j0+1)
                }

                basins.add(basin)
            }
        }
    }

    basins.sort()
    val res = basins[basins.size-1].toLong() *  basins[basins.size-2].toLong() *  basins[basins.size-3].toLong()

    println(res)
}


