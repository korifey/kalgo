
fun main(args: Array<String>) {
    val res = input.readLines().sumBy { invalidCharScore(it) }
    println(res)
}

data class Block(val start: Char, val end: Char, val score: Int)

fun invalidCharScore(s: String): Int {
    val stack = mutableListOf<Char>()
    val blocks = listOf(
        Block('(', ')', 3),
        Block('[', ']', 57),
        Block('{', '}', 1197),
        Block('<', '>', 25137)
    )
    for (c in s) {
        if (blocks.any { it.start == c }) {
            stack.add(c)
            continue
        }
        val b = blocks.singleOrNull { it.end == c } ?: error ("illegal char $c in string $s")

        if (stack.isEmpty() || stack.last() != b.start)
            return b.score

        stack.removeAt(stack.lastIndex)
    }

    return 0
}
