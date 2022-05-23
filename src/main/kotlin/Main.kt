fun minSwapsRequired(s: String): Int {
    val zeros = s.count { it == '0' }
    val ones = s.count { it == '1' }
    val even = s.length % 2 == 0
    if (even) {
        if (zeros % 2 != 0 || ones % 2 != 0) {
            return -1;
        }
    } else {
        val ok = (zeros % 2 == 0 && ones % 2 == 1) || (zeros % 2 == 1 && ones % 2 == 0)
        if (!ok) return -1
    }
    var wrongPlaces = 0
    for (i in 0..s.length / 2) {
        if (s[i] != s[s.length - i - 1]) {
            wrongPlaces++
        }
    }
    var result = wrongPlaces / 2
    if (!even) {
        if (ones % 2 == 1 && s[s.length / 2] == '0' || zeros % 2 == 1 && s[s.length / 2] == '1')
            result++
    }
    return result
}

fun main(args: Array<String>) {
    println(minSwapsRequired("0100101"))
}