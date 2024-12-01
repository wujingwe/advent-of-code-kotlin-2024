fun main() {
    fun part1(input: List<String>): Int {
        val (l, r) = input
            .map { it.split("   ") }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()

        val sortL = l.sorted()
        val sortR = r.sorted()

        return sortL.mapIndexed { index, value -> Math.abs(value - sortR[index]) }.sum()
    }

    fun part2(input: List<String>): Int {
        val (l, r) = input
            .map { it.split("   ") }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()

        val freq = r.groupingBy { it }.eachCount()
        return l.fold(0) { acc, value ->
            acc +value * (freq[value] ?: 0)
        }
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/inputs/Day01_test.txt` file:
    val testInput = readInput("res/Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/inputs/Day01.txt` file.
    val input = readInput("res/Day01")
    part1(input).println()
    part2(input).println()
}
