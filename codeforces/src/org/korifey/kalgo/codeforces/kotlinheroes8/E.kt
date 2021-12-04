import java.util.*
import kotlin.math.max
import kotlin.math.min

fun paired(char: Char) = if (char == '(') ')' else '('

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    outer@ for (case in 1..t) {
        val n = scanner.nextInt()
        scanner.nextLine()
        val s = scanner.nextLine()
        val a = scanner.nextLine()

        val res = CharArray(n) { ' ' }

        for (i in 0 until n - 3) {
            if (a[i] == '1') {
                res[i] = '('
            }
        }
        var ok = true

        for (i in 0 until n - 3) {
            if (a[i] == '1') {
                if (res[i + 3] != ' ') {
                    ok = false
                }
                res[i + 3] = ')'
                if (res[i + 1] != ' ') {
                    val c = paired(res[i + 1])
                    if (res[i + 2] != ' ' && c != res[i + 2]) {
                        ok = false
                    } else {
                        res[i + 2] = c
                    }
                } else if (res[i + 2] != ' ') {
                    val c = paired(res[i + 2])
                    res[i + 1] = c
                } else {
                    if (s[i + 1] == '(') {
                        res[i + 1] = '('
                        res[i + 2] = ')'
                    } else {
                        res[i + 1] = ')'
                        res[i + 2] = '('
                    }
                }
            }
        }

        if (!ok) {
            println(-1)
            break
        }

        var ans = 0
        for (i in 0 until n) {
            val t = (res[i] != ' ' && res[i] != s[i])
            ans += if (t) 1 else 0
        }
        println(ans)
    }
}