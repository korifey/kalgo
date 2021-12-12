import java.util.*

fun main(args: Array<String>) {
    val a = input.readLines().map { s -> s.map { c -> c -'0'}.toIntArray() }.toTypedArray()
    val n = a.size
    val m = a[0].size

    val nsteps = 100
    var res = 0


    for (step in 1..nsteps) {
        val flashed = Array(n) { BooleanArray(m) }
        val flashing = ArrayDeque<Pair<Int, Int>>()

        fun add(i: Int, j: Int) {
            if (i<0 || i>=n || j<0 || j>= m)
                return

            if (++a[i][j] > 9 && !flashed[i][j]) {
                flashing.add(i to j)
                flashed[i][j] = true
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                add(i, j)
            }
        }

        while (flashing.isNotEmpty()) {
            val (i,j) = flashing.poll()

            add(i-1,j-1)
            add(i-1,j)
            add(i-1,j+1)

            add(i, j-1)
            add(i, j+1)

            add(i+1,j-1)
            add(i+1,j)
            add(i+1,j+1)

            res++
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (a[i][j] > 9) {
                    a[i][j] = 0
                    require(flashed[i][j])
                }
            }
        }
    }
    println(res)
}

