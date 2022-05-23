fun getTotalImbalance(weight: Array<Int>): Long {
    // Write your code here
    var result = 0L
    for (i in 1 until weight.size) {
        var currentMax = weight[i]
        var currentMin = weight[i]
        for (j in i - 1 downTo 0) {
            if (weight[j] > currentMax) currentMax = weight[j]
            if (weight[j] < currentMin) currentMin = weight[j]
            result += (currentMax - currentMin)
        }
    }

    return result;
}

fun main(args: Array<String>) {
    println(getTotalImbalance(arrayOf(1, 2, 3)))
}