private class Solution4 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double { //num1 2, nums2 2
        var i = 0
        var j = 0
        var prev = 0
        var cur = 0
        while (i + j < (nums1.size + nums2.size) / 2 + 1 ) { // 3
            prev = cur

            if (i == nums1.size)
                cur = nums2[j++]
            else if (j == nums2.size)
                cur = nums1[i++]

            else if (nums1[i] < nums2[j])
                cur = nums1[i++]
            else
                cur = nums2[j++]

        }

        if ((nums1.size + nums2.size) % 2 != 0) //false
            return cur.toDouble()
        else
            return (prev.toDouble() + cur.toDouble()) / 2
    }
}

fun main(args: Array<String>) {
    val res = Solution4().findMedianSortedArrays(intArrayOf(1,2), intArrayOf(3,4))
    println(res)
}

