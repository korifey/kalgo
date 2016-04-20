package org.korifey.kalgo.util

inline fun Int.digits(action: (Int) -> Unit) {
    var n = this

    if (n == 0) action(0)

    while (n > 0) {
        action (n % 10)
        n /= 10
    }
}
