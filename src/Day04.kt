val DIRS = listOf(
    1 to 0,
    -1 to 0,
    0 to 1,
    0 to -1,
    1 to 1,
    1 to -1,
    -1 to 1,
    -1 to -1
)

fun main() {

    fun dfs(array: Array<Array<Char>>, x: Int, y: Int, dx: Int, dy: Int, s: String): Int {
        if (x < 0 || x >= array.size || y < 0 || y >= array[0].size) {
            return 0
        }
        val c = array[x][y]
        val newString = s + c
        if (newString.length > 4) {
            return 0
        }
        if (newString == "XMAS") {
            return 1
        }

        return dfs(array, x + dx, y + dy, dx, dy, newString)
    }

    fun part1(input: List<String>): Int {
        val array = Array(input.size) { i ->
            input[i].toCharArray().toTypedArray()
        }

        val starts = array.flatMapIndexed { i, row ->
            row.mapIndexedNotNull { j, value ->
                if (value == 'X') i to j else null
            }
        }

        return starts.fold(0) { acc, (x, y) ->
            var ans = 0
            for (dir in DIRS) {
                ans += dfs(array, x, y, dir.first, dir.second, "")
            }
            acc + ans
        }
    }

    fun isXmas(array: Array<Array<Char>>, x: Int, y: Int): Boolean {
        var count = 0
        val d1 = "${array[x][y]}${array[x + 1][y + 1]}${array[x + 2][y + 2]}"
        if (d1 == "MAS" || d1 == "SAM") {
            count++
        }
        val d2 = "${array[x + 2][y]}${array[x + 1][y + 1]}${array[x][y + 2]}"
        if (d2 == "MAS" || d2 == "SAM") {
            count++
        }
        return count == 2
    }

    fun part2(input: List<String>): Int {
        val array = Array(input.size) { i ->
            input[i].toCharArray().toTypedArray()
        }

        var res = 0
        for (i in 0..< array.size - 2) {
            for (j in 0 ..< array[0].size - 2) {
                if (isXmas(array, i, j)) {
                    res++
                }
            }
        }
        return res
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/inputs/Day01_test.txt` file:
    val testInput = readInput("res/Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/inputs/Day01.txt` file.
    val input = readInput("res/Day04")
    part1(input).println()
    part2(input).println()
}
