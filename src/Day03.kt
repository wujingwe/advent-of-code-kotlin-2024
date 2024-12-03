fun main() {

    fun part1(input: List<String>): Int {
        val s = input.joinToString("")
        val pattern = Regex("""mul\((\d+),(\d+)\)""")
        val matches =  pattern.findAll(s)
        return matches
            .map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
            .sumOf { (x, y) -> x * y }
    }

    fun part2(input: List<String>): Int {
        val s = input.joinToString("")
        val pattern = Regex("""do\(\)|don't\(\)|mul\((\d+),(\d+)\)""")
        val matches =  pattern.findAll(s)

        // sum to operation(do or don't)
        val (sum, _) = matches.fold((0 to true)) { acc, match ->
            if (match.value == "do()") {
                acc.first to true
            } else if (match.value == "don't()") {
                acc.first to false
            } else if (!acc.second) {
                acc
            } else {
                val x = match.groupValues[1].toInt()
                val y = match.groupValues[2].toInt()
                acc.first + x * y to true
            }
        }

        return sum
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/inputs/Day01_test.txt` file:
    val testInput = readInput("res/Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/inputs/Day01.txt` file.
    val input = readInput("res/Day03")
    part1(input).println()
    part2(input).println()
}
