class Solution32 {
    fun longestValidParentheses(s: String): Int {
        val a = IntArray(s.length)


        for (i in 1..s.lastIndex) {
            if (s[i] == '(') {
                if (s[i-1] == ')')
                    a[i] = a[i-1]
            } else { //s[i] == ')'
                val idx = i - a[i-1] - 1
                if (idx >= 0 && s[idx] == '(')
                    a[i] = a[idx] + a[i-1] +2
            }
        }

        return a.max () ?: 0
    }
}