import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

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

    val begin_idxs = t.withIndex().filter { it.value == 1 }.map { it.index }
    var sum_begin = begin_idxs.sumBy { k[it] }

    var sum_rem = m - sum_begin

    if (sum_rem < 0) {
        println(-1)
        return
    }

    val dp = Array(n + 1) { Array(sum_rem + 1) { -1 } }

    dp[0][0] = 0

    val begin_idxs_set = begin_idxs.toSet()

    for (i in 1..n) {
        dp[i] = dp[i - 1].copyOf()
        if (i - 1 in begin_idxs_set) {
            continue
        }
        for (w in 0 until sum_rem) {
            if (dp[i - 1][w] != -1 && w + k[i - 1] <= sum_rem) {
                dp[i][w + k[i - 1]] = i - 1
            }
        }
    }
    var best = 0

    var kek = (k.sum() - sum_begin)

    for (w in 0..kek) {
        if (dp[n][w] != -1) {
            if (abs(kek - w - w) < abs(kek - best - best)) {
                best = w
            }
        }
    }
    if (best > kek - best) {
        best = kek - best
    }

    if (sum_rem < max((kek - best) * 2 - 1, best * 2)) {
        println(-1)
        return
    }

    var cur_w = best
    var cur_n = n
    val ans_last = mutableListOf<Int>()
    while (cur_w > 0) {
        ans_last.add(dp[cur_n][cur_w])
        cur_w -= k[dp[cur_n][cur_w]]
        cur_n--
    }

    val ans = IntArray(n) {0}

    var cur_sum = 1
    for (idx in begin_idxs) {
        ans[idx] = cur_sum
        cur_sum += k[idx]
    }

    var cur_sum1 = cur_sum + 1


    for (idx in ans_last) {
        ans[idx] = cur_sum1
        cur_sum1 += k[idx] * 2
    }

    var cur_sum2 = cur_sum

    for (idx in (0 until n) - (begin_idxs) - (ans_last)) {
        ans[idx] = cur_sum2
        cur_sum2 += k[idx] * 2
    }

    print(ans.joinToString(" "))
}