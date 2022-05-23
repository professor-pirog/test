fun main() {
    println(solution("abbabba"))
}

fun solution(S: String): Int {

    outer@ for (i in 1 until S.length) {
        val currentLength = S.length - i
        for (j in 0 until currentLength) {
            if (S[j] != S[j + i]) {
                continue@outer
            }
        }
        return currentLength
    }

    return 0
}