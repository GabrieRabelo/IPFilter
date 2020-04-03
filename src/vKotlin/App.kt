package vKotlin

import java.io.File

fun main() {
    val initialTime = System.nanoTime()
//    val data = mutableListOf<Interval>()
    val filter = Filter(mutableListOf())

    File("src/vKotlin/resource/casoenunciado.txt")
        .forEachLine {
            val lineValues = it.split("-".toRegex())
            val interval = Interval(lineValues[0].toInt(), lineValues[1].toInt())
//            data.add(interval)
            filter.add(interval)
        }

//    data.sort()
//    println(data)

    print(filter)
    val endTime = System.nanoTime()
    val executionTime = endTime - initialTime
    println("\n\nTempo de execução: $executionTime")


}