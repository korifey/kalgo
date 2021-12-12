import java.util.*

fun main(args: Array<String>) {
    val a = input.readLines().map { s -> s.map { c -> c -'0'}.toIntArray() }.toTypedArray()
    val n = a.size
    val m = a[0].size

    var res = 0


    fun height(i: Int, j: Int): Int {
        if (i<0 || i>=n || j<0 || j>= m)
            return 10
        else return a[i][j]
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            val h = a[i][j]
            if (height(i-1,j) > h && height(i+1,j)>h && height(i,j-1) > h && height(i,j+1)>h)
                res+= h + 1
        }
    }

    println(res)
}