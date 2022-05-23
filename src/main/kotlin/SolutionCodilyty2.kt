import java.util.regex.Matcher
import java.util.regex.Pattern

fun main() {
    print(solution("A2Le", "2pL1"))
}

fun solution(S: String, T: String): Boolean {
    val s1iterator = IllegibleString(S).iterator()
    val s2iterator = IllegibleString(T).iterator()
    while (s1iterator.hasNext()) {
        if (!s2iterator.hasNext()) return false
        if (!s1iterator.next().compatibleWith(s2iterator.next())) return false
    }
    return !s2iterator.hasNext()
}

class IllegibleChar(
    val illegible: Boolean,

    val char: Char?
)

class IllegibleString(val origString: String) : Iterable<IllegibleChar> {

    override fun iterator(): Iterator<IllegibleChar> = IllegibleCharIterator()

    inner class IllegibleCharIterator : Iterator<IllegibleChar> {
        var pointer = 0
        var illegibleCharCounter = 0
        override fun hasNext(): Boolean {
            return illegibleCharCounter > 0 || pointer < origString.length
        }

        override fun next(): IllegibleChar {
            if (illegibleCharCounter == 0) {
                if (origString[pointer].isDigit()) {
                    val (number, endIndex) = origString.retrieveNumber(pointer)
                    pointer = endIndex
                    illegibleCharCounter = number
                }
            }

            return if (illegibleCharCounter > 0) {
                IllegibleChar(false, null).also { illegibleCharCounter-- }
            } else {
                IllegibleChar(true, origString[pointer]).also { pointer++ }
            }

        }

        private fun String.retrieveNumber(initIdx: Int): Pair<Int, Int> {
            var idx = initIdx
            while (idx < length && this[idx].isDigit()) idx++
            return substring(initIdx, idx).toInt() to idx
        }
    }
}

fun IllegibleChar.compatibleWith(another: IllegibleChar) =
    !illegible || !another.illegible || char == another.char
