package p13

import input

fun main(args: Array<String>) {
    var set = mutableSetOf<P>()
    var readFold = false


    for (line in input.readLines()) {
        if (line.isBlank())
            readFold = true
        else if (readFold) {
            val (axisKind, axisValue) = line.removePrefix("fold along ").split("=")
            val newSet = mutableSetOf<P>()
            set.mapTo(newSet) { p ->

                if (axisKind == "x")
                    p.reflectVerticalAxis(axisValue.toInt())
                else {
                    require(axisKind == "y") {axisKind}
                    p.reflectHorizontalAxis(axisValue.toInt())
                }
            }
            set = newSet
        } else {
            val (x,y) = line.split(',').map { it.toInt() }
            set.add(P(x,y))
        }
    }

    val res = Array(set.map { it.y }.maxOrNull()!! + 1) { BooleanArray(set.map { it.x }.maxOrNull()!! + 1) }
    set.forEach { p -> res[p.y][p.x] = true}


    for (b in res) {
        println(b.joinToString("") { if (it) "▒" else " " })
    }
}

