class Solution41 {
    fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size
        val a = nums

        for (i in 0..n-1) {
            while (a[i] != i+1) {
                if (a[i] <= 0 || a[i] > n) {
                    a[i] = 0
                    break;
                } else {
                    val j = a[i] - 1
                    if (a[j] == a[i]) {
                        a[i] = 0
                        break
                    }

                    else {
                        a[i] = a[j]
                        a[j] = j + 1
                    }
                }
            }
        }

        for (i in 0..n-1)
            if (a[i] != i+1)
                return i+1

        return n+1
    }
}