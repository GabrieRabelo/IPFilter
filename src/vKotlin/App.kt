package vKotlin

import java.io.File

fun main() {
    val initialTime = System.currentTimeMillis()
    val filter = Filter(mutableListOf())

    File("src/vKotlin/resource/cohen04.txt")
        .forEachLine {
            val lineValues = it.split("-".toRegex())
            val interval = Interval(lineValues[0].toInt(), lineValues[1].toInt())

            filter.add(interval)
        }

    println("Tamanho da lista " + filter.getSize())

    val endTime = System.currentTimeMillis()
    val executionTime = endTime - initialTime
    println("\n\nTempo de execução: $executionTime ms")


}