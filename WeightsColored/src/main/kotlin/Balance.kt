import java.util.*


fun main() {
    class Item(val color: Int, val id: Int, val weight: Int) {
        override fun toString(): String {
            return "Item(color=$color, id=$id, weight=$weight)"
        }
    }

    val npacks = 15
    val weightOfColor = 60_000;




    val md5ToColor = mutableMapOf<String, Int>()
    val md5ToId = mutableMapOf<String, Int>()

    //prepare
    val allItems = Item(0,0,0).javaClass.classLoader.getResourceAsStream("6.csv")!!
        .reader().buffered().readLines().drop(1).map { line ->
            val (colorHash, idHash, massStr) = line.split(",")

            Item(
                md5ToColor.getOrPut(colorHash) { md5ToColor.size },
                md5ToId.getOrPut(idHash) { md5ToId.size },
                massStr.toInt()
            )
    }

    val ncolors = md5ToColor.size

    // Represent one pack of items (weights).
    class Pack : Comparable<Pack> {
        //items sorted by weights from max to min
        val items = TreeSet(compareBy<Item> { - it.weight }.thenBy { it.id })

        // How many items of specified color exists (for speedup)
        val color2count = IntArray(ncolors)

        // Total weight of all items and all colors (for speedup)
        var aggregatedWeight = 0

        val distinctColors get() = color2count.count {it > 0}


        fun remove(item: Item) {
            require(items.remove(item))
            aggregatedWeight -= item.weight
            if (--color2count[item.color] == 0)
                aggregatedWeight -= weightOfColor
        }


        fun put(item: Item) {
            require(items.add(item))
            aggregatedWeight += item.weight
            if (color2count[item.color]++ == 0)
                aggregatedWeight += weightOfColor
        }

        fun weightAfterPut(item: Item) = aggregatedWeight + item.weight + (if (color2count[item.color] == 0) weightOfColor else 0)

        //For sorting packs by aggregated weight from max to min
        override fun compareTo(other: Pack): Int {
            val r1 = (aggregatedWeight - other.aggregatedWeight)
            if (r1 != 0) return r1
            return hashCode() - other.hashCode()
        }

        //ONLY FOR ASSERTION: checks aggregated weight is really equal to weight of all items + weight of different colors
        fun checkCorrect() {
            val actualDistinctColors = items.map { it.color }.distinct().count()
            val actualWeight = items.sumOf { it.weight } + actualDistinctColors * weightOfColor

            require(actualDistinctColors == distinctColors)
            require(actualWeight == aggregatedWeight)
        }

        override fun toString(): String {
            return "Pack(#items=${items.size}, aggregatedWeight=${"%,d".format(aggregatedWeight)}, distinctColors=$distinctColors)"
        }


    }


    var packsResult = Array(npacks) { Pack() }

    //stupid greedy
//    allItems.sortedBy { -it.weight }

//    for (w in allItems) {
//        val p = packs.minByOrNull { it.weightAfterPut(w) }!!
//        p.put(w)
//    }

    //items grouped by 1 color (sorted)
    val groupedByColor = allItems.groupBy { it.color }.values.sortedBy { itemOfColor -> - itemOfColor.sumOf { it.weight } }
    for ((i, l) in groupedByColor.withIndex()) {
        println("Color $i, weight: ${"%,d".format(l.sumOf { it.weight })}")
    }

    val totalMass = allItems.sumOf { it.weight }
    val optimalMassPerPack = (totalMass + weightOfColor*ncolors) / npacks
    var lo = optimalMassPerPack
    var hi = optimalMassPerPack * 10 //heuristic, put any reasonable number here for upper limit of binary search



    //binary search of threshold for pack maximum size
    outer@while (hi - lo > 100 )//heuristic
    {
        val threshold = (lo + hi) / 2


        //Just clone groupedByColor (immutable) into packByColor (mutable): packs are sorted by aggregate weights from max to min
        val packByColor = TreeSet(groupedByColor.map { items ->
            Pack().apply {
                items.forEach { put(it) }
            }
        })

        // Possible result of algorithm for given threshold
        // Invariant (keeps always): for (pack in packsCandidate) { require(pack.aggregateWeight <= threshold) }
        val packsCandidate = Array(npacks) { Pack() }

        //here we try to fill [packsCandidate] extracting from [packByColor]
        while (packByColor.isNotEmpty()) {

            val from = packByColor.last() //heaviest pack with items (all items of same color)
            require(packByColor.remove(from)) //remove the heaviest element from packByColor

            //try to put all pack [from] into some item from [packsCandidate]
            val storageThatCanAcceptFullColoredPack = packsCandidate.filter { threshold - it.aggregatedWeight >= from.aggregatedWeight }.
                minByOrNull { threshold - it.aggregatedWeight - from.aggregatedWeight }

            if (storageThatCanAcceptFullColoredPack != null) {
                from.items.forEach { storageThatCanAcceptFullColoredPack.put(it) }
            } else {
                // pack [from] can't be put fully into any pack from [packsCandidate]

                //chose pack [to] as the most empty one.
                // if we can't put any [from] item  here, we can't put any [from] item into other [packsCandidate] packs
                val to = packsCandidate.maxByOrNull { threshold - it.aggregatedWeight }!!

                //only need for verification that we able to put anything into pack [to]
                val sizeBeforeOperation = to.items.size

                //try to put all items that we can into [to] pack by greedy strategy
                from.items.sortedBy { -it.weight }.forEach {
                    if (to.weightAfterPut(it) < threshold) {
                        from.remove(it)
                        to.put(it)
                    }
                }

                if (to.items.size == sizeBeforeOperation) { //no one item put, so threshold is too low
                    lo = threshold
                    continue@outer
                }

                packByColor.add(from) //put [from] back but with reduced aggregatedWeight - TreeSet will keep sorted order from max to min
            }
        }
        hi = threshold
        packsResult = packsCandidate
    }


    println("Number of packs: $npacks")
    println("Mass of color: $weightOfColor")
    println("Number of weights (without colors): ${allItems.size}")
    println("Number of different colors: $ncolors")

    println("Total mass: ${"%,d".format(totalMass)}")

    println("Optimal mass per pack: ${"%,d".format(optimalMassPerPack)}")
    println("Result mass per pack: ${"%,d".format(hi)}")


    println("After tricky greedy:")
    packsResult.sortDescending()

    //ONLY FOR ASSERTION to verify everything is correct
    packsResult.forEach { it.checkCorrect() }
    require(allItems.size == packsResult.sumOf { it.items.size })
    require(allItems.toSet().equals(packsResult.flatMap { it.items }.toSet()))

    //print result
    for ((i, p) in packsResult.withIndex()) {
        println("pack $i: weight=${"%,d".format(p.aggregatedWeight)} colors=${p.distinctColors}")
    }

}