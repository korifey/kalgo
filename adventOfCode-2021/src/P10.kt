
fun main(args: Array<String>) {
    val res = input.readLines().map { completeionScore(it) }.filter { it >= 0 }

    if (res.size % 2 != 1) {
        error("Not odd size of line: ${res.size}")
    }
    println(res.sorted()[res.size/2])
}

data class Block(val start: Char, val end: Char, val score: Int)

fun completeionScore(s: String): Long {
    val stack = mutableListOf<Char>()
    val blocks = listOf(
        Block('(', ')', 1),
        Block('[', ']', 2),
        Block('{', '}', 3),
        Block('<', '>', 4)
    )
    for (c in s) {
        if (blocks.any { it.start == c }) {
            stack.add(c)
            continue
        }
        val b = blocks.singleOrNull { it.end == c } ?: error ("illegal char $c in string $s")

        if (stack.isEmpty() || stack.last() != b.start)
            return -1

        stack.removeAt(stack.lastIndex)
    }


    return stack.reversed().fold(0L) {acc, c -> acc*5 + blocks.single { c == it.start }.score}
}
