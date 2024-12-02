import kotlin.math.abs

fun main() {
    fun isIncreasing(list: List<Int>): Boolean {
        return list.zipWithNext().all { (first, second) ->
            val diff = abs(first - second)
            first < second && diff >= 1 && diff <= 3
        }
    }

    fun isDecreasing(list: List<Int>): Boolean {
        return list.zipWithNext().all { (first, second) ->
            val diff = abs(first - second)
            first > second && diff >= 1 && diff <= 3
        }
    }

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(" ").map { tokens -> tokens.toInt() } }
            .count { isIncreasing(it) || isDecreasing(it) }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(" ").map { tokens -> tokens.toInt() } }
            .count { list ->
                list.indices.any { removeIndex ->
                    val subList = list.filterIndexed { index, _ -> index != removeIndex }
                    isIncreasing(subList) || isDecreasing(subList)
                }
            }
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/inputs/Day01_test.txt` file:
    val testInput = readInput("res/Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/inputs/Day01.txt` file.
    val input = readInput("res/Day02")
    part1(input).println()
    part2(input).println()
}
