
fun main(args: Array<String>) {
    val a = input.readLines().map { it.toInt() }
    val res = a.withIndex().count { (i, v) -> i>0 && v > a[i-1] }
    println(res)
}