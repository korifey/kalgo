
fun main(args: Array<String>) {
    val a = input.readLines().map { it.toInt() }
    val b = (0..a.lastIndex-2).map { i -> a[i] + a[i+1] + a[i+2] }
    val res = b.indices.drop(1).count { b[it] > b[it-1] }
    println(res)
}