package p18

import input

fun main(args: Array<String>) {
    val nodes = input.readLines().map { parse(it) }
    var max = 0
    for (i in nodes.indices)
        for (j in nodes.indices) {
            if (i == j) continue
            println("-----------------------")
            println("$i:"+nodes[i])
            println("$j:"+nodes[j])
            val sum = nodes[i] + nodes[j]
            println(sum)
            println(sum.magnitude())
            max = kotlin.math.max(max, (nodes[i] + nodes[j]).magnitude())
        }
    println("result=$max")
}

