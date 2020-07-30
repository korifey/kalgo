import java.lang.Integer.min

class Solution {
    fun trap(height: IntArray): Int {
        val stack = mutableListOf<Int>()
        var waterLevel = 0
        var res = 0
        for (i in 0..height.lastIndex) {
            while (stack.size > 0) {
                val l = stack.last()
                val hl = height[l]
                res += (i - l - 1) * (min(height[i], hl) - waterLevel)
                waterLevel = hl

                if (height[i] >= height[l])
                    stack.removeAt(stack.lastIndex)
                else
                    break
            }

            stack.add(i)
            waterLevel = height[i]
        }

        return res
    }
}