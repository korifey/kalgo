import java.util.*
import kotlin.math.abs
import kotlin.math.max


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val m = scanner.nextInt()

    val k = IntArray(n)
    val t = IntArray(n)
    for (i in 0 until n) {
        k[i] = scanner.nextInt()
        t[i] = scanner.nextInt()
    }

    val answer = IntArray(n)
    var slots1 = 0
    var slots2 = 0
    for (i in 0 until n) {
        if (t[i] == 1) {
            answer[i] = slots1+1
            slots1 += k[i]
        } else { //t[i] == 2
            slots2 += k[i]
        }
    }

    if (m < slots1 + slots2) { //simply not enough space
        println(-1)
        return
    }

    val maps = Array<MutableMap<Int,Int>>(n) { mutableMapOf()}
    var prevMap = mutableMapOf(0 to 0)
    for (i in 0 until n) {
        if (t[i] != 2) continue

        for ((key, _) in prevMap) {
            maps[i][key-k[i]] = -1
            maps[i][key+k[i]] = 1
        }

        prevMap = maps[i]
    }

    var diff = (0..m).first { prevMap.containsKey(it) }
    if (m < slots1 + slots2 + max(diff - 1, 0)) { //extra cells
        println(-1)
        return
    }

    var oddIndex = slots1+1
    var evenIdx = slots1+2

    for (i in n-1 downTo 0) {
        if (t[i] != 2) continue

        val v = maps[i][diff]!!
        diff -= v*k[i]
        if (v > 0) {
            answer[i] = oddIndex
            oddIndex += k[i]*2
        } else {
            answer[i] = evenIdx
            evenIdx += k[i]*2
        }
    }

    print(answer.joinToString(" "))
}