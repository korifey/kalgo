import kotlin.math.max

class Solution1 {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()

        val state = IntArray(candidates.size)
        val res = mutableListOf<List<Int>>()

        fun rec(candidateN: Int, remaining: Int) {
            when {
                remaining < 0 -> return
                remaining == 0 -> {
                    res.add(state.withIndex().flatMap { indexed ->
                        List(indexed.value) { candidates[indexed.index] }
                    })
                }
                else -> {
                    if (candidateN >= candidates.size)
                        return



                    var rem = remaining
                    val originalState = state[candidateN]
                    while (rem > 0) {
                        rem -= candidates[candidateN]
                        state[candidateN]++
                        rec(candidateN + 1, rem)
                    }
                    state[candidateN] = originalState
                    rec(candidateN + 1, remaining)
                }
            }
        }

        rec(0, target)

        return res
    }
}

fun main(args: Array<String>) {
    val res = Solution1().combinationSum(intArrayOf(2,3,6,7), 7)
    res.forEach {r -> println(r)}
}

