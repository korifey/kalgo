
fun main(args: Array<String>) {
    val nodes = mutableMapOf<String, Int>()
    val graph = mutableListOf<MutableList<Int>>()

    fun strToNode(s: String)  = nodes[s] ?: nodes.size.also {
        require(nodes.put(s, it) == null)
        require(graph.size == it)
        graph.add(mutableListOf())
    }
    input.readLines().forEach { line ->
        val (from, to) = line.split("-")
        val i = strToNode(from)
        val j = strToNode(to)

        require(!graph[i].contains(j))
        graph[i].add(j)

        require(!graph[j].contains(i))
        graph[j].add(i)
    }
    require(graph.size == nodes.size)

    val start = nodes["start"] ?: error("no start")
    val end = nodes["end"]?: error("no finish")

    var res = 0
    val visited = IntArray(graph.size)
    val small = BooleanArray(graph.size)
    for ((k, v) in nodes)
        if (k[0].isLowerCase())
            small[v] = true

    var smallTwice = -1

    fun traverse(node: Int) {
        if (node == end) {
            res++
            return
        }

        visited[node] ++
        for (n in graph[node]) {
            if (small[n] && visited[n] > 0) {
                if (n != start && n != end && smallTwice == -1) {
                    smallTwice = n
                    traverse(n)
                    smallTwice = -1
                } else
                    continue

            } else
                traverse(n)
        }
        visited[node] --
    }

    traverse(start)
    println(res)

}

